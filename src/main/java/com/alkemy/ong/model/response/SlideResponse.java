package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class SlideResponse {

  @ApiModelProperty(example = "ID",
      position = 0)
  private Long slideId;
  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/name.png",
      position = 1)
  private String imageUrl;
  @ApiModelProperty(example = "This is a text",
      position = 2)
  private String text;
  @ApiModelProperty(example = "1",
      dataType = "integer",
      position = 3)
  private Integer order;

}
