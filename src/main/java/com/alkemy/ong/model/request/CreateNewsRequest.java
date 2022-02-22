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
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class CreateNewsRequest {

  @NotBlank(message = "Name cannot be null or empty.")
  @ApiModelProperty(example = "News number 1",
      required = true,
      position = 0)
  private String name;

  @NotBlank(message = "Text cannot be null or empty.")
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
