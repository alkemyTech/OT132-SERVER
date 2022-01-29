package com.alkemy.ong.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import com.alkemy.ong.exception.ErrorItemResult;
import com.alkemy.ong.exception.InputErrorException;
import com.alkemy.ong.model.request.ContactRequest;
import com.alkemy.ong.model.response.ContactResponse;
import com.alkemy.ong.model.response.ListContactResponse;
import com.alkemy.ong.service.abstraction.ICreateContact;
import com.alkemy.ong.service.abstraction.IGetContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {

  
  @Autowired
  private ICreateContact createContact;

  @Autowired
  public IGetContact getContact;

  @GetMapping
  public ResponseEntity<ListContactResponse> list() {
    ListContactResponse contactResponse = getContact.list();
    return ResponseEntity.ok(contactResponse);
  }

  @PostMapping
  public ResponseEntity<ContactResponse> add(@Valid @RequestBody ContactRequest contactRequest,
      BindingResult results) throws InputErrorException {

    List<ErrorItemResult> errors = new ArrayList<>();
    
    if (results.hasErrors()) {

      results.getFieldErrors().stream().forEach(e -> {
        errors.add(new ErrorItemResult(e.getField(), e.getDefaultMessage()));
      });

      throw new InputErrorException(errors.toString());

    } else {

      ContactResponse contactResponse = createContact.add(contactRequest);

      return ResponseEntity.ok(contactResponse);

    }
  } 
}
