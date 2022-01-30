package com.alkemy.ong.exception;

import com.alkemy.ong.exception.advice.ErrorResponse;
import java.sql.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
@ControllerAdvice
public class GlobalHandleException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EmptyInputException.class)
  public ResponseEntity<ErrorResponse> handleEmptyInput(EmptyInputException e) {
    ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(),
        new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
  }

}
