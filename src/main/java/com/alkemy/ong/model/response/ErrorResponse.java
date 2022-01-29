package com.alkemy.ong.model.response;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

  private int status;
  private String message;
  private Timestamp timestamp;

}
