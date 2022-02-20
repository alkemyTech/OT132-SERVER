package com.alkemy.ong.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class ActivityResponse {

  @ApiModelProperty(example = "Activity number 1",
      position = 0)
  private String name;

  @ApiModelProperty(example = "This is content",
      position = 1)
  private String content;

  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/image.png",
      position = 2)
  private String image;

}
