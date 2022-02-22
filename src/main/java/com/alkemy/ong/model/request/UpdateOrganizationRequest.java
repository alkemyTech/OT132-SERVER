package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UpdateOrganizationRequest {

  @NotBlank(message = "Name field can not be null or empty.")
  @ApiModelProperty(example = "Somos mas",
      required = true,
      position = 0)
  private String name;

  @NotBlank(message = "Image field can not be null or empty.")
  @ApiModelProperty(example = "image.png",
      required = true,
      position = 1)
  private String image;

  @Email(message = "Email has invalid format.")
  @NotBlank(message = "Email field can not be null or empty.")
  @ApiModelProperty(example = "somosmas@gmail.com",
      required = true,
      position = 2)
  private String email;

  @NotBlank(message = "Welcome text field can not be null or empty.")
  @ApiModelProperty(example = "Welcome!!!",
      required = true,
      position = 3)
  private String welcomeText;

  @ApiModelProperty(example = "Address 123",
      position = 4)
  private String address;

  @ApiModelProperty(example = "3123124124",
      dataType = "integer",
      position = 5)
  private Integer phone;

  @ApiModelProperty(example = "About us",
      position = 6)
  private String aboutUsText;

  @ApiModelProperty(example = "facebook.com",
      position = 7)
  private String facebookUrl;

  @ApiModelProperty(example = "linkedin.com",
      position = 8)
  private String linkedinUrl;

  @ApiModelProperty(example = "instagram.com",
      position = 9)
  private String instagramUrl;


}
