package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.ContactRequest;
import com.alkemy.ong.model.response.ContactResponse;

public interface ICreateContact {

  ContactResponse add(ContactRequest contactRequest);

}
