package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemberRequest {

  @NotBlank(message = "Name is mandatory")
  @Pattern(regexp = "^[A-Za-z]*$", message = "The name has to contain only letters")
  private String name;
  private String facebookUrl;
  private String instagramUrl;
  private String linkedinUrl;
  @NotBlank(message = "Image is mandatory")
  private String image;
  private String description;
}
