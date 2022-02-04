package com.alkemy.ong.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUs {

  private String organizationName;
  private String address;
  private int phone;

  public ContactUs(String organizationName, String address, int phone) {
    this.organizationName = organizationName;
    this.address = address;
    this.phone = phone;
  }
}
