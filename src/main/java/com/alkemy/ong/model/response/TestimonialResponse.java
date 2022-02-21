package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestimonialResponse {

  @ApiModelProperty(value = "testimonialsId",
      example = "1",
      position = 0)
  private Long testimonialsId;
  @ApiModelProperty(value = "name",
      example = "name",
      position = 1)
  private String name;
  @ApiModelProperty(value = "image",
      example = "image.jpg",
      position = 2)
  private String image;
  @ApiModelProperty(value = "content",
      example = "content",
      position = 3)
  private String content;


}
