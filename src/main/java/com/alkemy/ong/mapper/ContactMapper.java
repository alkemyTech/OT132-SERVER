package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.response.ContactResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

  public ContactResponse map(Contact contact) {
    ContactResponse contactResponse = new ContactResponse();
    contactResponse.setContactId(contact.getContactId());
    contactResponse.setName(contact.getName());
    contactResponse.setEmail(contact.getEmail());
    contactResponse.setPhone(contact.getPhone());
    contactResponse.setMessage(contact.getMessage());
    return contactResponse;
  }

}
