package com.alkemy.ong.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExpection {

  @ExceptionHandler(value = UploadErrorException.class)
  public ResponseEntity<ErrorObject> handleUploadErrorException(UploadErrorException e) {
    ErrorObject error = new ErrorObject();
    error.setStatus(HttpStatus.CONFLICT.value());
    error.setMessage(e.getMessage());
    error.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<ErrorObject>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = InputErrorException.class)
  public ResponseEntity<ErrorObject> handleInputErrorException(InputErrorException e) {
    ErrorObject error = new ErrorObject();
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setMessage(e.getMessage());
    error.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<ErrorObject>(error, HttpStatus.BAD_REQUEST);
  }

}
