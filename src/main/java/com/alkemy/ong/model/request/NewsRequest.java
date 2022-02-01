package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequest {

  @NotBlank(message = "The field name cannot be blank.")
  private String name;

  @NotBlank(message = "The field text cannot be blank.")
  private String text;

  @NotBlank(message = "The field image cannot be blank.")
  private String image;
}
