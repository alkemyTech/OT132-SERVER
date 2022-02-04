package com.alkemy.ong.controller;

import com.alkemy.ong.mapper.OrganizationUpdateMapper;
import com.alkemy.ong.model.request.OrganizationRequest;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.OrganizationUpdateResponse;
import com.alkemy.ong.service.abstraction.IGetOrganizationDetails;
import com.alkemy.ong.service.abstraction.IOrganizationUpdate;
import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("organization")
public class OrganizationController {

  @Autowired
  private IGetOrganizationDetails getOrganizationDetails;

  @Autowired
  private IOrganizationUpdate organizationUpdate;

  @Autowired
  private OrganizationUpdateMapper mapper;

  @GetMapping("/public")
  public ResponseEntity<OrganizationResponse> getOrganizationDetails() {
    OrganizationResponse organizationResponse = getOrganizationDetails.find();
    return ResponseEntity.ok().body(organizationResponse);
  }

  @PostMapping("/public")
  public ResponseEntity<OrganizationUpdateResponse> update(
      @RequestBody @Valid OrganizationRequest organizationRequest){
    organizationUpdate.update(organizationRequest);
    return ResponseEntity.ok().body(mapper.map(organizationRequest));
  }

}
