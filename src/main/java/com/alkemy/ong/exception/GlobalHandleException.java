package com.alkemy.ong.exception;

import com.alkemy.ong.common.TimestampUtils;
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
    ErrorResponse error = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
    ErrorResponse error = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    for (FieldError fieldError : e.getFieldErrors()) {
      error.add(fieldError.getDefaultMessage());
    }
    error.setTimestamp(TimestampUtils.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      InvalidCredentialsException e) {
    ErrorResponse error = buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }

  private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(httpStatus.value());
    error.add(message);
    error.setTimestamp(TimestampUtils.now());
    return error;
  }

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
    ErrorResponse error = buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setTimestamp(TimestampUtils.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
}