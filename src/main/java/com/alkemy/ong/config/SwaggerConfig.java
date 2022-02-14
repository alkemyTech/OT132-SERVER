package com.alkemy.ong.config;

import java.security.Principal;
import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket customDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.alkemy.ong.controller"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
        .ignoredParameterTypes(Principal.class)
        .apiInfo(apiInfo());
  }

  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder
        .builder()
        .operationsSorter(OperationsSorter.METHOD)
        .tagsSorter(TagsSorter.ALPHA)
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("Somos Más ONG",
        "Alkemy Project",
        "1.0",
        "termsOfService",
        ApiInfo.DEFAULT_CONTACT,
        "Apache 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0",
        new ArrayList<>());
  }
}
