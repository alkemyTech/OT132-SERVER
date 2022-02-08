package com.alkemy.ong.common.mail.template;

import com.alkemy.ong.common.mail.IContent;
import com.alkemy.ong.common.mail.IMail;
import java.text.MessageFormat;

public class WelcomeEmailTemplate implements IContent, IMail {

  private static final String TYPE = "text/plain";
  private static final String SUBJECT = "Contact successfully registered";
  private static final String THANK_TEXT = "Thanks {0} for register in our contact list";

  private final String emailTo;
  private final String contactName;

  public WelcomeEmailTemplate(String email, String name) {
    this.emailTo = email;
    this.contactName = name;
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

  @Override
  public String getBody() {
    return MessageFormat.format(THANK_TEXT, contactName);
  }

  @Override
  public String getContentType() {
    return TYPE;
  }

}
