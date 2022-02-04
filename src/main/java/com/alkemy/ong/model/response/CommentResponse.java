package com.alkemy.ong.model.response;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {

  private String body;
  private Timestamp timestamp;

}
