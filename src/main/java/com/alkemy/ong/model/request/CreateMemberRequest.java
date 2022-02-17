package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class CreateMemberRequest {

  @NotBlank(message = "Name cannot be null or empty.")
  @Pattern(regexp = "^[A-Za-z]*$", message = "The name has to contain only letters")
  @ApiModelProperty(example = "Jane",
      required = true,
      position = 0)
  private String name;
  @ApiModelProperty(example = "Facebook.url",
      required = false,
      position = 0)
  private String facebookUrl;
  @ApiModelProperty(example = "Instagram.url",
      required = false,
      position = 0)
  private String instagramUrl;
  @ApiModelProperty(example = "Linkedin.url",
      required = false,
      position = 0)
  private String linkedinUrl;
  @NotBlank(message = "Image cannot be null or empty.")
  @ApiModelProperty(example = "image.png",
      required = true,
      position = 0)
  private String image;
  @ApiModelProperty(example = "This is a descritpion",
      required = true,
      position = 0)
  private String description;
}
