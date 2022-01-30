package com.alkemy.ong.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  @Email(message = "The e-mail is not valid")
  private String email;

  @NotBlank
  @Min(5)
  @Max(8)
  private String password;

  @JsonInclude(Include.NON_EMPTY)
  private String role;


}
