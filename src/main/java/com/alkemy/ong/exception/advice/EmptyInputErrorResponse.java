package com.alkemy.ong.exception.advice;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class EmptyInputErrorResponse {

  private HttpStatus status;
  private String message;
  private Timestamp timestamp;

}
