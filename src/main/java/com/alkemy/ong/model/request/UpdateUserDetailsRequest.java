package com.alkemy.ong.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDetailsRequest {

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  private String photo;
}
