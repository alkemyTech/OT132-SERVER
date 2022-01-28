package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.service.abstraction.IGetOrganizationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("organization")
public class OrganizationController {

  @Autowired
  private IGetOrganizationDetails getOrganizationDetails;

  @GetMapping("/public")
  public ResponseEntity<OrganizationResponse> getOrganizationDetails() {
    OrganizationResponse organizationResponse = getOrganizationDetails.find();
    return ResponseEntity.ok().body(organizationResponse);
  }

}
