package com.alkemy.ong.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

  private String email;
  private String jwt;

  public AuthenticationResponse(String email, String jwt) {
    this.email = email;
    this.jwt = jwt;
  }
}
