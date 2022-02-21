package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.request.UpdateOrganizationRequest;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.service.abstraction.IGetOrganizationDetails;
import com.alkemy.ong.service.abstraction.IUpdateOrganization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "Organization Endpoints", value = "OrganizationEndpoints")
public class OrganizationController {

  @Autowired
  private IGetOrganizationDetails getOrganizationDetails;

  @Autowired
  private IUpdateOrganization updateOrganization;


  @GetMapping("/public")
  @ApiOperation(value = "Return organization details")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The organization's details.",
          response = OrganizationResponse.class)})
  public ResponseEntity<OrganizationResponse> getOrganizationDetails() {
    OrganizationResponse organizationResponse = getOrganizationDetails.find();
    return ResponseEntity.ok().body(organizationResponse);
  }

  @PostMapping("/public")
  @ApiOperation(value = "Update the organization details")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The organization was successfully updated",
          response = OrganizationResponse.class),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)})
  @ApiImplicitParam(name = "Authorization", value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public ResponseEntity<OrganizationResponse> update(
      @RequestBody @Valid UpdateOrganizationRequest updateOrganizationRequest) {
    return ResponseEntity.ok()
        .body(updateOrganization.update(updateOrganizationRequest));
  }

}