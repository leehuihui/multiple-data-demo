package com.ldd.multipledatademo.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi // swagger3开启注解，与swagger2不同
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApis() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)//是否启用：注意生产环境需要关闭
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .ignoredParameterTypes(CookieValue.class)
                .apiInfo(apiInfo())
                .select()
                //以下拦截配置可以三选一，根据需要进行添加
                .apis(RequestHandlerSelectors.basePackage("com.ldd.multipledatademo.controller"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("使用swagger生成的接口文档")
                .description("test")
                // 服务条款URL
                .termsOfServiceUrl("https://www.baidu.com/")
                // 作者信息
                .contact(new Contact("test", "https://www.baidu.com/", "xx@xx.com"))
                .version("0.0.1")
                .build();
    }
}
