package com.alkemy.ong.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponse {

  private long contactId;

  private String name;

  private Integer phone;

  private String email;

  private String message;

}
