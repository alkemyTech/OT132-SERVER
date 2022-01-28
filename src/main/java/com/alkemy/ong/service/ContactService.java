package com.alkemy.ong.service;

import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.response.ContactResponse;
import com.alkemy.ong.model.response.ListContactResponse;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.service.abstraction.IGetContact;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IGetContact {

  @Autowired
  private IContactRepository contactRepository;

  @Autowired
  private ContactMapper contactMapper;

  @Override
  public ListContactResponse list() {
    List<Contact> contacts = contactRepository.findAll();
    return buildListContactResponse(contacts);

  }

  private ListContactResponse buildListContactResponse(List<Contact> contacts) {
    List<ContactResponse> contactResponse = new ArrayList<>(contacts.size());

    for (Contact contact : contacts) {
      contactResponse.add(contactMapper.map(contact));
    }

    ListContactResponse listContactResponse = new ListContactResponse();
    listContactResponse.setContactResponses(contactResponse);
    return listContactResponse;
  }


}
