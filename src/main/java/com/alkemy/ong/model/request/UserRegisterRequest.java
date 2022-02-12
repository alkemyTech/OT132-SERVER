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
public class UserRegisterRequest {

  @NotBlank(message = "First name cannot be empty or null.")
  @ApiModelProperty(example = "Jane",
      required = true,
      position = 0)
  private String firstName;

  @NotBlank(message = "Last name cannot be empty or null.")
  @ApiModelProperty(example = "Doe",
      required = true,
      position = 1)
  private String lastName;

  @NotBlank(message = "Email cannot be empty or null.")
  @Email(message = "Email is not valid.")
  @ApiModelProperty(example = "jane@mail.com",
      required = true,
      position = 2)
  private String email;

  @NotBlank(message = "Password cannot be empty or null.")
  @ApiModelProperty(example = "12345",
      required = true,
      position = 3)
  private String password;

}
