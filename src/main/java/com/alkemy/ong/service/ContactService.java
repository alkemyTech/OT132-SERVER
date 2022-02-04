package com.alkemy.ong.service;

import com.alkemy.ong.common.mail.EmailUtils;
import com.alkemy.ong.common.mail.template.WelcomeEmailTemplate;
import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.request.CreateContactRequest;
import com.alkemy.ong.model.response.ContactResponse;
import com.alkemy.ong.model.response.ListContactResponse;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.service.abstraction.ICreateContact;
import com.alkemy.ong.service.abstraction.IGetContactDetails;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IGetContactDetails, ICreateContact {

  private final Logger logger = LoggerFactory.getLogger(ExternalServiceException.class);

  @Autowired
  private IContactRepository contactRepository;

  @Autowired
  private ContactMapper contactMapper;

  @Autowired
  private EmailUtils emailUtils;

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

  @Override
  public ContactResponse create(CreateContactRequest contactRequest, String email)
      throws ExternalServiceException {
    Contact contact = contactMapper.map(contactRequest);
    WelcomeEmailTemplate template = new WelcomeEmailTemplate(email, contact.getName());
    try {
      emailUtils.send(template);
    } catch (ExternalServiceException e) {
      logger.error(e.getMessage());
    }
    return contactMapper.map(contactRepository.save(contact));
  }

}
