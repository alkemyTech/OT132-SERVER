package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class UpdateNewsRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  @ApiModelProperty(example = "News number 1",
      required = true,
      position = 0)
  private String name;

  @NotBlank(message = "The content cannot be empty or null.")
  @ApiModelProperty(example = "This is a description",
      required = true,
      position = 1)
  private String text;

  @NotBlank(message = "Image cannot be null or empty.")
  @ApiModelProperty(example = "image.png",
      required = true,
      position = 2)
  private String image;

}
