package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {

  @NotBlank(message = "Field NAME can't be null nor empty")
  @Size(min = 2, max = 70, message = "Name can have between 2 and 70 characters")
  private String name;


  @NotNull(message = "Field PHONE can't be null")
  private Integer phone;

  @Email(message = "Email is not valid")
  @NotBlank(message = "Field EMAIL can't be null nor empty")
  @Size(min = 5, max = 150, message = "Email need to have between 5 and 150 characters")
  private String email;

  @NotBlank(message = "Field MESSAGE can't be null nor empty")
  @Size(max = 500, message = "message can contain a maximum of 500 characters")
  private String message;

}
