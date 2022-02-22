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
public class CategoryResponse {

  @ApiModelProperty(example = "1",
      position = 0)
  private Long idCategory;

  @ApiModelProperty(example = "Category number 1",
      position = 1)
  private String name;

  @ApiModelProperty(example = "This is a description",
      position = 2)
  private String description;

  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/image.png",
      position = 3)
  private String image;
}
