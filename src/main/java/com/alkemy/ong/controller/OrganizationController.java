package com.alkemy.ong.controller;


import com.alkemy.ong.model.request.UpdateOrganizationRequest;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.service.abstraction.IGetOrganizationDetails;
import com.alkemy.ong.service.abstraction.IUpdateOrganization;
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
  private IUpdateOrganization updateOrganization;

  @GetMapping("/public")
  public ResponseEntity<OrganizationResponse> getOrganizationDetails() {
    OrganizationResponse organizationResponse = getOrganizationDetails.find();
    return ResponseEntity.ok().body(organizationResponse);
  }

  @PostMapping("/public")
  public ResponseEntity<OrganizationResponse> update(
      @RequestBody @Valid UpdateOrganizationRequest updateOrganizationRequest) {
    return ResponseEntity.ok()
        .body(updateOrganization.update(updateOrganizationRequest));
  }
}
