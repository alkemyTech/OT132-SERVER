package com.alkemy.ong.common.mail;

import java.text.MessageFormat;

public class RegisterEmailTemplate implements IContent, IMail {

  private static final String TYPE = "text/plain";
  private static final String SUBJECT = "Register Successfully";
  private static final String WELCOME_TEXT = "Welcome to {0}";

  private String organizationName;
  private String emailTo;
  private String image;
  private String address;
  private Integer phone;

  public RegisterEmailTemplate(String organizationName, String emailTo, String image,
      String address, Integer phone) {
    this.organizationName = organizationName;
    this.emailTo = emailTo;
    this.image = image;
    this.address = address;
    this.phone = phone;
  }

  @Override
  public String getBody() {
    return image
        + "\n" + MessageFormat.format(WELCOME_TEXT, organizationName)
        + "\n" + WELCOME_TEXT
        + "\n" + "Address: " + address
        + "\n" + "Telephone: " + phone;
  }

  @Override
  public String getContentType() {
    return TYPE;
  }

  @Override
  public String getSubject() {
    return SUBJECT;
  }

  @Override
  public IContent getContent() {
    return this;
  }

  @Override
  public String getTo() {
    return emailTo;
  }
}
