package com.alkemy.ong.exception.advice;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

  private HttpStatus status;
  private String message;
  private Timestamp timestamp;

  public ErrorResponse(HttpStatus status, String message, Timestamp timestamp) {
    this.status = status;
    this.message = message;
    this.timestamp = timestamp;
  }


}
