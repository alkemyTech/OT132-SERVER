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

  @ApiModelProperty(example = "code",
      position = 0)
  private int status;
  @ApiModelProperty(example = "[\n  message\n]",
      position = 1)
  private List<String> messages;
  @ApiModelProperty(example = "timestamp",
      position = 2)
  private Timestamp timestamp;

  public ErrorResponse() {
    this.messages = new ArrayList<>();
  }

  public void add(String message) {
    this.messages.add(message);
  }

}