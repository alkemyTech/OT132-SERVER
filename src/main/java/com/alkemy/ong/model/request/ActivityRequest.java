package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityRequest {

  @NotBlank(message = "Name can not be black")
  private String name;

  @NotBlank(message = "Content can not be black")
  private String content;

  @NotBlank(message = "Image can not be black")
  private String image;

}
