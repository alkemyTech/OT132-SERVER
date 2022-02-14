package com.alkemy.ong.exception.defaulterrors;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class Error400 {

  @ApiModelProperty(dataType = "integer",
      example = "400",
      position = 0)
  private int status;
  @ApiModelProperty(example = "[\n  Bad Request\n]",
      position = 1)
  private List<String> messages;
  @ApiModelProperty(dataType = "string",
      example = "2020-02-01T21:26:22Z",
      position = 2)
  private Timestamp timestamp;
}

