package com.alkemy.ong.exception.advice;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

  private int status;
  private List<String> messages;
  private Timestamp timestamp;

  public void add(String message) {
    messages.add(message);
  }

}
