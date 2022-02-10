package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTestimonialRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  private String name;

  private String image;

  @NotBlank(message = "Content cannot be empty or null.")
  private String content;
}
