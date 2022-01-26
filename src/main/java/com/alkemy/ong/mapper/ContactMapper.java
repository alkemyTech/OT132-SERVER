package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.response.ContactResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public static ContactResponse map(Contact contact) {
        ContactResponse cR = new ContactResponse();
        cR.contactId = contact.getContactId();
        cR.name = contact.getName();
        cR.email = contact.getEmail();
        cR.phone = contact.getPhone();
        cR.message = contact.getMessage();
        return cR;
    }

}
