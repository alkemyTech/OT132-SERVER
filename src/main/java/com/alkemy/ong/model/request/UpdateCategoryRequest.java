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
public class UpdateCategoryRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "The name has to contain only letters")
  @ApiModelProperty(example = "Category number 1",
      required = true,
      position = 0)
  private String name;

  @ApiModelProperty(example = "This is a description",
      position = 1)
  private String description;

  @ApiModelProperty(example = "image.png",
      position = 2)
  private String image;
}
