package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTestimonialRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  @ApiModelProperty(example = "Jane",
      required = true,
      position = 0)
  private String name;

  @ApiModelProperty(example = "image.jpg",
      position = 1)
  private String image;

  @NotBlank(message = "Content cannot be empty or null.")
  @ApiModelProperty(example = "content",
      required = true,
      position = 3)
  private String content;

}
