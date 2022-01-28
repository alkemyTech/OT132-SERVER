package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.response.ContactResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

  public static ContactResponse map(Contact contact) {
    ContactResponse contactResponse = new ContactResponse();
    contactResponse.contactId = contact.getContactId();
    contactResponse.name = contact.getName();
    contactResponse.email = contact.getEmail();
    contactResponse.phone = contact.getPhone();
    contactResponse.message = contact.getMessage();
    return contactResponse;
  }

}
