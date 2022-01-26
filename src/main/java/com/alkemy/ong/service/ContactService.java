package com.alkemy.ong.service;

import java.util.ArrayList;
import java.util.List;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.response.ContactResponse;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.service.abstraction.IGetContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ContactService implements IGetContact {

    @Autowired
    IContactRepository repo;

    @Override
    public List<ContactResponse> find() {

        List<Contact> contacts = repo.findAll();
        List<ContactResponse> contactResponse = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getDeletedAt()==null)
            contactResponse.add(ContactMapper.map(contact));

        }

        return contactResponse;
    }

}
