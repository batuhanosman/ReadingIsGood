package com.getir.readingisgood.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {
/*
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("reading-is-good-api")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo());
    }
*/
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("API").version(appVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
/*
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Getir",
                "Reading Is Good ",
                "1.0.0",
                null,
                new Contact("Batuhan Osman DEMIRTAS", "https://github.com/batuhanosman", "osman.demirtas95@gmail.com"),
                null, null, Collections.emptyList());
    }
*/
}
