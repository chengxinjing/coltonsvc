package com.colton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
                .title("AWS_WEB API ")
                .description("aws web api")
                .version("1.0.0").contact(new Contact("Colton","https://github.com/chengxinjing/coltonsvc/tree/master/aws_web","530501797@qq.com"))
                .build()).select().apis(RequestHandlerSelectors.basePackage("com.colton.controller"))
                .paths(PathSelectors.any()).build();
    }
}
