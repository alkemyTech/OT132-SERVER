package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExpection {

  @ExceptionHandler
  public ResponseEntity<ErrorObject> handleUploadErrorException(UploadErrorException e) {
    ErrorObject error = new ErrorObject();
    error.setStatus(HttpStatus.CONFLICT.value());
    error.setMessage(e.getMessage());
    error.setTimestamp(System.currentTimeMillis());
    return new ResponseEntity<ErrorObject>(error,HttpStatus.CONFLICT);
  }
}
