package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

  @NotBlank(message = "First name cannot be empty or null.")
  private String firstName;

  @NotBlank(message = "Last name cannot be empty or null.")
  private String lastName;

  @NotBlank(message = "Email cannot be empty or null.")
  @Email(message = "Email is not valid.")
  private String email;

  @NotBlank(message = "Password cannot be empty or null.")
  private String password;

}
