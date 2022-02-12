package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UserResponse {

  @ApiModelProperty(example = "Jane",
      position = 0)
  private String firstName;
  @ApiModelProperty(example = "Doe",
      position = 1)
  private String lastName;
  @ApiModelProperty(example = "jane@mail.com",
      position = 2)
  private String email;
  @ApiModelProperty(example = "picture.png",
      position = 3)
  private String photo;
  @JsonInclude(Include.NON_EMPTY)
  @ApiModelProperty(example = "role.USER",
      position = 4)
  private String role;
  @ApiModelProperty(example = "token",
      position = 5)
  private String token;

}
