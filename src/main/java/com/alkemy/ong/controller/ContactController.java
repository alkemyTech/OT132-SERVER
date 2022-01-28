package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.ContactResponse;
import com.alkemy.ong.service.abstraction.IGetContact;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {

  @Autowired
  public IGetContact getContact;

  @GetMapping
  public ResponseEntity<Map<String, List<ContactResponse>>> list() {
    List<ContactResponse> contactResponse = getContact.list();
    Map<String, List<ContactResponse>> map = new HashMap<>();
    map.put("Contacts", contactResponse);

    return ResponseEntity.ok(map);
  }
}
