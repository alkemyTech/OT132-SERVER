package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNewsRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  private String name;

  @NotBlank(message = "The content cannot be empty or null.")
  private String text;

  private String image;

}
