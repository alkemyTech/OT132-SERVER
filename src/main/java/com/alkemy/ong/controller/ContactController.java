package com.alkemy.ong.controller;

import java.util.List;
import com.alkemy.ong.model.response.ContactResponse;
import com.alkemy.ong.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping
    public ResponseEntity<List<ContactResponse>> getAll() {
        List<ContactResponse> contactResponse = service.find();
        if (contactResponse.isEmpty())
            return ResponseEntity.noContent().build();//will probably change to a costum exception once controller advice is done

        else
            return ResponseEntity.ok(contactResponse);


    }



}
