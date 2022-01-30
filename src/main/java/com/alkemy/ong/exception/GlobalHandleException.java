package com.alkemy.ong.exception;

import java.sql.Timestamp;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHandleException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      InvalidCredentialsException e) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(HttpStatus.UNAUTHORIZED.value());
    error.add(e.getMessage());
    error.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorResponse error = new ErrorResponse();
    for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
      error.setStatus(HttpStatus.BAD_REQUEST.value());
      error.add(objectError.getDefaultMessage());
      error.setTimestamp(new Timestamp(System.currentTimeMillis()));
    }
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
