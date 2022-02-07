package com.alkemy.ong.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

  private String email;
  private String token;

  public AuthenticationResponse(String email, String token) {
    this.email = email;
    this.token = token;
  }
}
