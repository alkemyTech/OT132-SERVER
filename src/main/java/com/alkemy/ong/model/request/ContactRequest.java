package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {

  @NotBlank(message = "Field Name can't be null nor empty")
  @Size(min = 2, max = 70, message = "Name can have between 2 and 70 characters")
  private String name;

  @NotEmpty(message = "Field phone can't be empty")
  private Integer phone;

  @Email(message = "Email is not valid")
  @NotBlank(message = "Field Email can't be null nor empty")
  @Size(min = 5, max = 150, message = "Email need to have between 5 and 150 characters")
  private String email;

  @NotBlank(message = "Field message can't be null nor empty")
  @Size(max = 500, message = "message can only be 500 characters long")
  private String message;

}
