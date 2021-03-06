package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CreateContactRequest;
import com.alkemy.ong.model.response.ContactResponse;

public interface ICreateContact {

  ContactResponse create(CreateContactRequest contactRequest);

}
