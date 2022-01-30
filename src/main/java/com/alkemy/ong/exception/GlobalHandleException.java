package com.alkemy.ong.exception;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

  @ExceptionHandler(value = ExternalServiceException.class)
  public ResponseEntity<ErrorResponse> handleExternalServiceException(ExternalServiceException e) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.add(e.getMessage());
    error.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setMessages(this.errorList(ex.getFieldErrors()));

    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private List<String> errorList(List<FieldError> fieldErrors) {

    List<String> errors = new ArrayList<>();
    for (FieldError fieldError : fieldErrors) {
      errors.add(fieldError.getDefaultMessage());
    }
    return errors;
  }

}
