package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

  private String firstName;
  private String lastName;
  private String email;
  private String photo;
  @JsonInclude(Include.NON_EMPTY)
  private String role;
  private String token;

}
