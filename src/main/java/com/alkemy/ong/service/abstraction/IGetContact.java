package com.alkemy.ong.service.abstraction;

import java.util.List;
import com.alkemy.ong.model.response.ContactResponse;

public interface IGetContact {

    List<ContactResponse> find();

}
