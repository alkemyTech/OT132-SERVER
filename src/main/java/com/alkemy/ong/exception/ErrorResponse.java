package com.alkemy.ong.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class ErrorResponse {

  @ApiModelProperty(example = "40# or 500",
      position = 0)
  private int status;
  @ApiModelProperty(example = "[\n  message\n]",
      position = 1)
  private List<String> messages;
  @ApiModelProperty(dataType = "string",
      example = "2020-02-01T21:26:22Z",
      position = 2)
  private Timestamp timestamp;

  public ErrorResponse() {
    this.messages = new ArrayList<>();
  }

  public void add(String message) {
    this.messages.add(message);
  }

}