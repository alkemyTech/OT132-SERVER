package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.request.CreateContactRequest;
import com.alkemy.ong.model.response.ContactResponse;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.model.response.ListContactResponse;
import com.alkemy.ong.service.abstraction.ICreateContact;
import com.alkemy.ong.service.abstraction.IGetContactDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
@Api(tags = "Contact Endpoints", value = "ContactEndpoints")
public class ContactController {

  @Autowired
  private ICreateContact createContact;

  @Autowired
  public IGetContactDetails getContactDetails;

  @GetMapping(consumes = {"application/json"},
      produces = {"application/json"})
  @ApiOperation(value = "Return the list of contacts")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The list of contacts.",
          response = ListCommentResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass =
              String.class,
          example =
              "Bearer access_token")})
  public ResponseEntity<ListContactResponse> list() {
    return ResponseEntity.ok(getContactDetails.list());
  }

  @PostMapping(consumes = {"application/json"},
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create a contact")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The contact was successfully created"),
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
  public ResponseEntity<ContactResponse> create(
      @Valid @RequestBody CreateContactRequest contactRequest) {
    ContactResponse contactResponse = createContact.create(contactRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(contactResponse);
  }

}
