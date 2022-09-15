package com.kamall.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger 配置类
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kamall.portal.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("Kamisora", "www.kamisora.xyz", "rakamiso575@gamil.com"))
                .title("kamall前台电商系统 - 在线API接口文档")
                .description("此文档为接kamall电商系统前台系统接口文档")
                .build();

    }
}