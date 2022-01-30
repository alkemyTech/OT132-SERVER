package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  @Size(min = 2, max = 70, message = "Name can have between 2 and 70 characters.")
  private String name;

  private Integer phone;

  @Email(message = "Email is not valid.")
  @NotBlank(message = "Email cannot be empty or null.")
  @Size(min = 5, max = 150, message = "Email need to have between 5 and 150 characters.")
  private String email;

  private String message;

}
