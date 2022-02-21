package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UpdateUserDetailsRequest {

  @ApiModelProperty(example = "Jane",
      position = 0)
  private String firstName;

  @ApiModelProperty(example = "Doe",
      position = 1)
  private String lastName;

  @ApiModelProperty(example = "jane@mail.com",
      position = 2)
  private String email;

  @ApiModelProperty(example = "foo123",
      position = 3)
  private String password;

  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/picture.png",
      position = 4)
  private String photo;
}
