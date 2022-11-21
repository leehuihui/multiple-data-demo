package com.ldd.multipledatademo.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <p>TODO</p>
 *
 * @author lidada
 * @date 2022/11/21
 */
@Configuration
@MapperScan(basePackages = "com.ldd.multipledatademo.dao.user.mapper", sqlSessionTemplateRef = "userSqlSessionTemplate", sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfig {


    @Autowired
    private MybatisPlusInterceptor mybatisPlusInterceptor;


    @Value("${spring.datasource.user.type}")
    private String dataSourceType;

    @Value("${spring.datasource.user.mapperLocations}")
    private String mapperLocations;

    @Value("${spring.datasource.user.typeAliasesPackage}")
    private String typeAliasesPackage;

    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource dataSource() throws ClassNotFoundException {
        if (StringUtils.isNotEmpty(dataSourceType)) {
            final Class<DataSource> aClass = (Class<DataSource>) Class.forName(dataSourceType);
            return DataSourceBuilder.create().type(aClass).build();
        }
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPlugins(mybatisPlusInterceptor);
        if (StringUtils.isNotEmpty(dataSourceType)) {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
        }
        if (StringUtils.isNotEmpty(typeAliasesPackage)) {
            sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        }
        return sessionFactory.getObject();
    }

    @Bean(name = "userTransactionManager")
    public DataSourceTransactionManager userTransactionManager(@Qualifier("userDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("userSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
