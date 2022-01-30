package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequest {

  @NotBlank(message = "This field cannot be blank.")
  private String name;

  @NotBlank(message = "This field cannot be blank.")
  private String text;

  @NotBlank(message = "This field cannot be blank.")
  private String image;
}
