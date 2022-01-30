package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

  @Email(message = "Email has invalid format.")
  @NotBlank(message = "Emails can not be blank.")
  private String email;

  @NotBlank(message = "Password can not be blank.")
  private String password;
}
