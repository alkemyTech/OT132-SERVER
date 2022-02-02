package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequest {

  @NotBlank(message = "Name cannot be null or empty.")
  private String name;

  @NotBlank(message = "Text cannot be null or empty.")
  private String text;

  @NotBlank(message = "Image cannot be null or empty.")
  private String image;
}
