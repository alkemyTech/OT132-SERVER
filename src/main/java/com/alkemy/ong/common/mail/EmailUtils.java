package com.alkemy.ong.common.mail;

import com.alkemy.ong.exception.ExternalServiceException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

  @Value("${app.sendgrid.key}")
  private String apiKey;

  @Value("${organization.from.mail}")
  private String from;

  public void send(IMail mail) throws ExternalServiceException {
    try {
      Content content = new Content();
      content.setType(mail.getContent().getContentType());
      content.setValue(mail.getContent().getBody());

      Mail sendgridMail = new Mail();
      sendgridMail.setSubject(mail.getSubject());
      sendgridMail.setFrom(new Email(from));
      sendgridMail.setReplyTo(new Email(mail.getTo()));
      sendgridMail.addContent(content);

      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(sendgridMail.build());

      SendGrid sendGrid = new SendGrid(apiKey);
      Response response = sendGrid.api(request);

      if (response.getStatusCode() != 202) {
        throw new ExternalServiceException("Fails to send email: " + response.getBody());
      }

    } catch (IOException | RuntimeException e) {
      throw new ExternalServiceException(e.getMessage());
    }

  }

}