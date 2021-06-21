package com.gaoshu.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    // 配置了swagger2的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        // 获取项目的环境路径 通过判断是是否处于自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("gaoshu")
                // 是否启用swagger
                .enable(flag)

                .select()
                // RequestHandlerSelectors,配置要扫描接口的方式
                // basePackage指定的包
                .apis(RequestHandlerSelectors.basePackage("com.gaoshu.swagger.controller"))
                //paths 过滤什么路径
//                .paths(PathSelectors.ant("/gaoshu/**"))
                .build();
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("gaoshu", "www.gaoshu.top", "123.qq.com");
        return new ApiInfo(
                "gaoshu的api文档",
                "这是一个api文档",
                "1.0",
                "urn:tos：指向一个网站",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
    
}
