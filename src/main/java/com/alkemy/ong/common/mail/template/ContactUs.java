package com.alkemy.ong.common.mail.template;

import lombok.Getter;

@Getter
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
