package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.ListMembersResponse;
import com.alkemy.ong.service.abstraction.IGetMemberDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

  @Autowired
  private IGetMemberDetails getMemberDetails;

  @GetMapping
  public ResponseEntity<ListMembersResponse> list() {
    ListMembersResponse listMembersResponse = getMemberDetails.findAll();
    return ResponseEntity.ok().body(listMembersResponse);
  }
}
