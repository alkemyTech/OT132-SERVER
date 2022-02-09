package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest { //validaciones 

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "The field name can only contain letters")
  private String name;

  private String description;

  private String image;
  
}
