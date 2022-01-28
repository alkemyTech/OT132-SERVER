package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUsersResponse {

  @JsonProperty("users")
  private List<UserResponse> userResponses;

}
