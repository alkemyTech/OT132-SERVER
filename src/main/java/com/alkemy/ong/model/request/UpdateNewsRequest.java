package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNewsRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  private String name;

  @NotBlank(message = "The content cannot be empty or null.")
  private String text;

  @NotBlank(message = "Image cannot be null or empty.")
  private String image;

}
