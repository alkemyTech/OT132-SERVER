package com.alkemy.ong.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  
   /* @Bean public Docket costumDoket() { return new Docket(DocumentationType.SWAGGER_2)
    .apiInfo(apiInfo()) .securityContexts(Arrays.asList(securityContext()))
    .securitySchemes(Collections.singletonList(apiKey())) .select()
    .apis(RequestHandlerSelectors.basePackage("com.alkemy.org.controller"))
    .paths(PathSelectors.any()) .build();
    
   //.securityContexts(Collections.singletonList(securityContext())); }
   
    }*/

  @Bean
  public Docket costumDoket() {
    return new Docket(DocumentationType.SWAGGER_2)
    .select()
    //.apis(RequestHandlerSelectors.basePackage("com.alkemy.org.controller"))
    .apis(RequestHandlerSelectors.any())
    .paths(PathSelectors.any())
    .build()
    .apiInfo(apiInfo());

    // .securityContexts(Collections.singletonList(securityContext()));
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("Somos Más ONG", "Alkemy Project", "1.0", "termsOfService",
        ApiInfo.DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
        new ArrayList<>());
  }

  
    /*private ApiKey apiKey() { return new ApiKey("JWT", "Authorization", "header"); }
    
    private SecurityContext securityContext() { return SecurityContext.builder()
    .securityReferences(defaultAuth()) .build(); }
    
    private List<SecurityReference> defaultAuth() { AuthorizationScope authorizationScope = new
    AuthorizationScope("global", "accessEverything"); AuthorizationScope[] authorizationScopes =
    new AuthorizationScope[1]; authorizationScopes[0] = authorizationScope; return
    Collections.singletonList(new SecurityReference("JWT", authorizationScopes)); }
   */


}
