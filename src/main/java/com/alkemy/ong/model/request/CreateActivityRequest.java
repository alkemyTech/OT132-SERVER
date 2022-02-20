package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class CreateActivityRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  @ApiModelProperty(example = "Activity number 1",
      required = true,
      position = 0)
  private String name;

  @NotBlank(message = "Content cannot be empty or null.")
  @ApiModelProperty(example = "This is content",
      required = true,
      position = 1)
  private String content;

  @NotBlank(message = "Image cannot be empty or null.")
  @ApiModelProperty(example = "image.png",
      required = true,
      position = 2)
  private String image;

}
