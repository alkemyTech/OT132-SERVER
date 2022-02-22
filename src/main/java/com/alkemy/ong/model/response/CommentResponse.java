package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {

  @ApiModelProperty(example = "Jane",
      position = 0)
  private String username;

  @ApiModelProperty(example = "This is a comment",
      position = 0)
  private String body;

  @ApiModelProperty(dataType = "string",
      example = "2020-02-01T21:26:22Z",
      position = 1)
  private Timestamp timestamp;

}
