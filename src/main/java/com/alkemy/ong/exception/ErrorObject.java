package com.alkemy.ong.exception;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorObject {
  
  private int status;
  private String message;
  private Timestamp timestamp;

}