package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ContactResponse;
import java.util.List;

public interface IGetContact {

  List<ContactResponse> list();

}
