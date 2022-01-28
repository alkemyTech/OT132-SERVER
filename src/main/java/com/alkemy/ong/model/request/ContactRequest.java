package com.alkemy.ong.model.request;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {

  @NotBlank(message = "Field Name can't be null nor empty")
  private String name;

  //@NotBlank(message = "Field phone can't be null nor empty")
  private Integer phone;

  @Email(message = "Email is not valid")
  @NotBlank(message = "Field Email can't be null nor empty")
  private String email;

  @NotBlank(message = "Field message can't be null nor empty")
  private String message;

  
}
