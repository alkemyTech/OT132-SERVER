package com.alkemy.ong.exception;

import com.alkemy.ong.exception.advice.EmptyInputErrorResponse;
import java.sql.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(httpStatus.value());
    error.add(message);
    error.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return error;
  }

  @ExceptionHandler(EmptyInputException.class)
  public ResponseEntity<EmptyInputErrorResponse> handleEmptyInput(EmptyInputException e) {
    EmptyInputErrorResponse response = new EmptyInputErrorResponse();
    response.setStatus(HttpStatus.BAD_REQUEST);
    response.setMessage(e.getMessage());
    response.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<EmptyInputErrorResponse>(response, HttpStatus.BAD_REQUEST);
  }
}
