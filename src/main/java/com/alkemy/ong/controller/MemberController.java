package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.model.request.UpdateMemberRequest;
import com.alkemy.ong.model.response.ListMembersResponse;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.service.abstraction.ICreateMember;
import com.alkemy.ong.service.abstraction.IGetMemberDetails;
import com.alkemy.ong.service.abstraction.IUpdateMember;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

  @Autowired
  private IGetMemberDetails getMemberDetails;

  @Autowired
  private ICreateMember createMember;

  @Autowired
  private IUpdateMember updateMember;

  @GetMapping
  public ResponseEntity<ListMembersResponse> list() {
    ListMembersResponse listMembersResponse = getMemberDetails.findAll();
    return ResponseEntity.ok().body(listMembersResponse);
  }

  @PostMapping
  public ResponseEntity<MemberResponse> create(
      @Valid @RequestBody CreateMemberRequest createMemberRequest) {
    MemberResponse memberResponse = createMember.save(createMemberRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody
      UpdateMemberRequest updateMemberRequest) {
    updateMember.update(id, updateMemberRequest);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
