package com.alkemy.ong.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorObject {
  
  private int status;
  private String message;
  private long timestamp;

}
