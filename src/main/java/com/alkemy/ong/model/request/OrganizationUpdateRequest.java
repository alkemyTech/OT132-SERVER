package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationUpdateRequest {

  @NotBlank(message = "Name field can not be null or empty.")
  private String name;

  @NotBlank(message = "Image field can not be null or empty.")
  private String image;

  @Email(message = "Email has invalid format.")
  @NotBlank(message = "Email field can not be null or empty.")
  private String email;

  @NotBlank(message = "Welcome text field can not be null or empty.")
  private String welcomeText;

  private String address;

  private Integer phone;

  private String aboutUsText;

  private String facebookUrl;

  private String linkedinUrl;

  private String instagramUrl;


}
