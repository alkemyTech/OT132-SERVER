package com.alkemy.ong.common.mail;

import com.alkemy.ong.model.request.ContactUs;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Value;

public class RegisterEmailTemplate implements IContent, IMail {

  @Value("${organization.mail}")
  private String EMAIL_TO;

  private static final String TYPE = "text/plain";
  private static final String SUBJECT = "Register Successfully";
  private static final String WELCOME_TEXT = "Welcome to {0}";
  private static final String CONTACT_US = "Contact us:";
  private final String ORGANIZATION_NAME;
  private final String ADDRESS;
  private final Integer PHONE;

  public RegisterEmailTemplate(ContactUs contactUs) {
    this.ORGANIZATION_NAME = contactUs.getOrganizationName();
    this.ADDRESS = contactUs.getAddress();
    this.PHONE = contactUs.getPhone();
  }

  @Override
  public String getBody() {
    return MessageFormat.format(WELCOME_TEXT, ORGANIZATION_NAME)
        + "\n" + CONTACT_US
        + "\n" + "Address: " + ADDRESS
        + "\n" + "Telephone: " + PHONE;
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
    return EMAIL_TO;
  }
}
