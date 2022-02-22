package com.alkemy.ong.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class ContactResponse {

  @ApiModelProperty(example = "1",
      position = 0)
  private long contactId;

  @ApiModelProperty(example = "Jane",
      dataType = "string",
      position = 1)
  private String name;

  @ApiModelProperty(example = "12313412",
      dataType = "integer",
      position = 2)
  private Integer phone;

  @ApiModelProperty(example = "jane@email.com",
      dataType = "string",
      position = 3)
  private String email;


  @ApiModelProperty(example = "This is a message",
      dataType = "string",
      position = 4)
  private String message;

}
