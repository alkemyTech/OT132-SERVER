package com.alkemy.ong.exception;

import java.io.IOException;

public class ExternalServiceException extends IOException {

  public ExternalServiceException(){}

  public ExternalServiceException(String message){
    super(message);
  }

  public ExternalServiceException(String message, Throwable cause){
    super(message, cause);
  }
  public ExternalServiceException(Throwable cause){
    super(cause);
  }

}
