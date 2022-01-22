package com.alkemy.ong.common;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

  public Response send(Mail mail) throws IOException {
    Response response = null;
    try {
      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      SendGrid sendGrid = new SendGrid("foo");
      response = sendGrid.api(request);
    } catch (IOException ex) {
      throw new IOException(ex.getMessage());
    }
    return response;
  }

  public Mail PrepareEmail(String subject, String to, String content, Content contentType) {
    Mail mail = new Mail();
    mail.setFrom(new Email(subject));
    mail.setSubject(subject);
    mail.setReplyTo(new Email(to));
    mail.addContent(contentType);
    return mail;
  }

}