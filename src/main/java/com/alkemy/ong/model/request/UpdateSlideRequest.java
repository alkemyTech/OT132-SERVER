package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UpdateSlideRequest {

  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/image.png",
      position = 0)
  private String imageUrl;
  @ApiModelProperty(example = "1",
      dataType = "integer",
      position = 1)
  private Integer order;
  @ApiModelProperty(example = "This is a text",
      position = 2)
  private String text;
}
