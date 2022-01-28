package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
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
  private List<Role> roles;
}
