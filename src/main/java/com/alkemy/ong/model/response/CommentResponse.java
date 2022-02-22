package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {

  private String username;
  private String body;
  private Timestamp timestamp;

}
