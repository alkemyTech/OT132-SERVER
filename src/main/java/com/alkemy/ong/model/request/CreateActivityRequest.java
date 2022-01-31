package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateActivityRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  private String name;

  @NotBlank(message = "Content cannot be empty or null.")
  private String content;

  @NotBlank(message = "Image cannot be empty or null.")
  private String image;

}
