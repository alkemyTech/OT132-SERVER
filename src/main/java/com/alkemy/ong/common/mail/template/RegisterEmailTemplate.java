package com.alkemy.ong.common.mail.template;

import com.alkemy.ong.common.mail.IContent;
import com.alkemy.ong.common.mail.IMail;
import com.alkemy.ong.common.mail.template.ContactUs;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Value;

public class RegisterEmailTemplate implements IContent, IMail {

  @Value("${organization.mail}")
  private String emailTo;

  private static final String TYPE = "text/plain";
  private static final String SUBJECT = "Register Successfully";
  private static final String WELCOME_TEXT = "Welcome to {0}";
  private static final String CONTACT_US = "Contact us:";

  private final String organizationName;
  private final String address;
  private final Integer phone;

  public RegisterEmailTemplate(ContactUs contactUs) {
    this.organizationName = contactUs.getOrganizationName();
    this.address = contactUs.getAddress();
    this.phone = contactUs.getPhone();
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
