package com.xin.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 类功能描述:Swagger2配置类
 *
 * @author Eternal
 * @date 2018/7/22 0:17
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${personal.swagger-enable}")
    private boolean swaggerEnable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).enable(swaggerEnable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xin.admin.sys.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Xin-Admin-Web RESTFUL API")
                .description("DEMO使用Swagger2构建RESTFUL API")
                .contact(new Contact("Eternal", "https://github.com/huangxinxin666", "431918700@qq.com"))
                .version("0.0.1")
                .build();
    }
}
