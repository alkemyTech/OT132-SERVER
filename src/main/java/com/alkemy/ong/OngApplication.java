package com.alkemy.ong;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class OngApplication {

  public static void main(String[] args) {
    SpringApplication.run(OngApplication.class, args);

  }

}
