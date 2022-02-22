package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class NewsResponse {

  @ApiModelProperty(example = "1",
      position = 0)
  private Long newsId;

  @ApiModelProperty(example = "News number 1",
      position = 1)
  private String name;

  @ApiModelProperty(example = "This is a description",
      position = 2)
  private String text;

  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/image.png",
      position = 3)
  private String image;

  @ApiModelProperty(example = "Name of the category",
      position = 4)
  private String categoryName;

}
