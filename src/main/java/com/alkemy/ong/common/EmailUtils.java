package com.alkemy.ong.common;

import com.alkemy.ong.exception.ExternalServiceException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import java.lang.reflect.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

  @Value("${app.sendgrid.key}")
  private String apiKey;

  public void send(Class<?> iMail) throws ExternalServiceException {

    try {

      Field subject = iMail.getField("subject");
      Field to = iMail.getField("to");
      Field content = iMail.getField("content");
      Field contentType = iMail.getField("contentType");

      prepareMail(subject.toString(), to.toString(), content.toString(), contentType.toString());

    } catch (NoSuchFieldException | IOException | RuntimeException e) {
      throw new ExternalServiceException(e.getMessage());
    }
  }

  public void prepareMail(String subject, String to, String content, String contentType)
      throws ExternalServiceException {

    validateInformation(subject, to, content, contentType);

    Mail mail = new Mail();
    Content content0 = new Content();

    content0.setType(contentType);
    content0.setValue(content);

    mail.setFrom(new Email(subject));
    mail.setReplyTo(new Email(to));
    mail.addContent(content0);

    Response response = null;
    try {

      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      SendGrid sendGrid = new SendGrid(apiKey);

      response = sendGrid.api(request);

      if ((response.getStatusCode() != 202)) {
        throw new ExternalServiceException("Fails to send email: " + response.getBody());
      }

    } catch (IOException | RuntimeException ex) {
      throw new ExternalServiceException(ex.getMessage());

    }
  }

  private void validateInformation(String subject, String to, String content, String contentType)
      throws ExternalServiceException {
    if (subject == null || subject.isEmpty() || subject.contains("@") == false) {
      throw new ExternalServiceException("The field 'subject' is not valid");
    }
    if (to == null || to.isEmpty() || to.contains("@") == false) {
      throw new ExternalServiceException("The field 'to' subject is not valid");
    }
    if (content == null || content.isEmpty()) {
      throw new ExternalServiceException("The field 'content' is empty");
    }
    if (contentType == null || contentType.isEmpty()) {
      throw new ExternalServiceException("The field 'contentType' is empty");
    }
  }

}