package com.alkemy.ong.common.mail.template;

import com.alkemy.ong.common.mail.IContent;
import com.alkemy.ong.common.mail.IMail;
import java.text.MessageFormat;

public class RegisterEmailTemplate implements IContent, IMail {

  private static final String TYPE = "text/plain";
  private static final String SUBJECT = "Register Successfully";
  private static final String WELCOME_TEXT = "Welcome to {0}";
  private static final String CONTACT_US = "Contact us:";

  private final String organizationName;
  private final String address;
  private final Integer phone;
  private final String emailTo;

  public RegisterEmailTemplate(ContactUs contactUs, String emailTo) {
    this.organizationName = contactUs.getOrganizationName();
    this.address = contactUs.getAddress();
    this.phone = contactUs.getPhone();
    this.emailTo = emailTo;
  }

  @Override
  public String getBody() {
    return MessageFormat.format(WELCOME_TEXT, organizationName)
        + "\n" + CONTACT_US
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
