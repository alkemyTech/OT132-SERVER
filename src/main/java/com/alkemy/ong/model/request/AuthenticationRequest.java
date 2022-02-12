package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class AuthenticationRequest {

  @Email(message = "Email has invalid format.")
  @NotBlank(message = "Emails cannot be null or empty.")
  @ApiModelProperty(example = "jane@mail.com",
      required = true,
      position = 0)
  private String email;

  @NotBlank(message = "Password cannot be null or empty.")
  @ApiModelProperty(example = "12345",
      required = true,
      position = 1)
  private String password;
}
