package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ContactResponse;

public interface IGetContact {

    List<ContactResponse> find();
    
}
