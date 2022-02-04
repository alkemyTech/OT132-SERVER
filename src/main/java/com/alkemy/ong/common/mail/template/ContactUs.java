package com.alkemy.ong.common.mail.template;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUs {

  private final String organizationName;
  private final String address;
  private final int phone;

  public ContactUs(String organizationName, String address, int phone) {
    this.organizationName = organizationName;
    this.address = address;
    this.phone = phone;
  }
}
