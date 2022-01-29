package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

  @NotBlank(message = "this field can not be blank")
  private String email;

  @NotBlank(message = "this field can not be blank")
  private String password;
}
