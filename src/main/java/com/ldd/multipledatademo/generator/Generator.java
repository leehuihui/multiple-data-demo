package com.ldd.multipledatademo.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.SQLException;
import java.util.Collections;

/**
 * <p>TODO</p>
 *
 * @author lidada
 * @date 2022/11/18
 */

public class Generator {


    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/order_db";
        String username = "root";
        String password = "root";
        // 初始化数据库脚本
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("lidada") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:/xx/xxx/multiple-data-demo/src/main/java/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ldd.multipledatademo") // 设置父包名
                            .moduleName("order") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/xx/xxx/multiple-data-demo/src/main/resources/mapper/order")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("order") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_")
                            ; // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
