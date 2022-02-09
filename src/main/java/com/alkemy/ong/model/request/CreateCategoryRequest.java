package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest { //validaciones 

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "The name field has to contain only letters")
  private String name;

  private String description;

  private String image;
  
}
