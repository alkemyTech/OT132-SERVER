package com.alkemy.ong.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class AuthenticationResponse {

  @ApiModelProperty(example = "jane@mail.com",
      position = 0)
  private String email;
  @ApiModelProperty(example = "token",
      position = 1)
  private String token;

  public AuthenticationResponse(String email, String token) {
    this.email = email;
    this.token = token;
  }

  public AuthenticationResponse() {
  }
}
