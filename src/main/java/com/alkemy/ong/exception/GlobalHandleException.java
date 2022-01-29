package com.alkemy.ong.exception;

import java.sql.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

  @ExceptionHandler(value = EmptyInputException.class)
  public ResponseEntity<ErrorResponse> handleEmptyInput(EmptyInputException e) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.add(e.getMessage());
    error.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
  }
}
