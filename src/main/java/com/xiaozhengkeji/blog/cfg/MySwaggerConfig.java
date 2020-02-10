package com.xiaozhengkeji.blog.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class MySwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("v1"))
                .groupName("v1")
                .select()
                //指定提供接口所在的基包
                .apis(RequestHandlerSelectors.basePackage("com.xiaozhengkeji.blog.api"))
                .build();
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     *
     * @return
     */
    private ApiInfo apiInfo(String version) {
        return new ApiInfoBuilder()
                .title("小正的博客")
                .description("API开发文档")
                .contact(new Contact("小正", "github/XiaoZhengRS", "Admin@xiaozhengkeji.com"))
                .version(version)
                .termsOfServiceUrl("localhost:8888/v1/")
                .build();
    }
}
