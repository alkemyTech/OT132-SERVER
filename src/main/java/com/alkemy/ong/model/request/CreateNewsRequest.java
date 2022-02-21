package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewsRequest {

  @NotBlank(message = "Name cannot be null or empty.")
  private String name;

  @NotBlank(message = "Text cannot be null or empty.")
  private String text;

  @NotBlank(message = "Image cannot be null or empty.")
  private String image;
}
