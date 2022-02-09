package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest { //validaciones 

  @NotBlank
  @Pattern(regexp = "^[A-Za-z]")
  private String name;

  private String description;

  private String image;
  
}
