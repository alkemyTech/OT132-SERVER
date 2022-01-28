package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.ListContactResponse;
import com.alkemy.ong.service.abstraction.IGetContact;
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
  public ResponseEntity<ListContactResponse> list() {

    ListContactResponse contactResponse = getContact.list();

    return ResponseEntity.ok(contactResponse);
  }
}
