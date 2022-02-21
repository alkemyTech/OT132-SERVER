package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateSlideRequest;
import com.alkemy.ong.model.request.UpdateSlideRequest;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.service.abstraction.ICreateSlide;
import com.alkemy.ong.service.abstraction.IDeleteSlide;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import com.alkemy.ong.service.abstraction.IUpdateSlide;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slides")
@Api(tags = "Slide Endpoints", value = "SlideEndpoints")
public class SlideController {

  @Autowired
  private IGetSlideDetails getSlideDetails;

  @Autowired
  private ICreateSlide createSlide;

  @Autowired
  private IDeleteSlide deleteSlide;

  @Autowired
  private IUpdateSlide updateSlide;

  @GetMapping(produces = {"application/json"})
  @ApiOperation(value = "Return the list of slides")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The list of slides.",
          response = ListSlideResponse.class),
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
  public ResponseEntity<ListSlideResponse> list() {
    return ResponseEntity.ok().body(getSlideDetails.list());
  }

  @PostMapping(consumes = {"application/json"},
      produces = {"application/json"})
  @ApiOperation(value = "Create a slide")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The slide was successfully created"),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "x-file-name", value = "File name",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "name"),
      @ApiImplicitParam(name = "x-content-typee", value = "Content type",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "image/png"),
      @ApiImplicitParam(name = "Authorization", value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")
  })
  public ResponseEntity<SlideResponse> create(
      @RequestBody @Valid CreateSlideRequest request,
      @RequestHeader("x-file-name") String fileName,
      @RequestHeader("x-content-type") String contentType) throws ExternalServiceException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(createSlide.create(request, fileName, contentType));
  }

  @DeleteMapping(value = "/{id}", produces = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete a slide passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The slide was successfully deleted"),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Slide not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the slide we want to delete",
          required = true, allowEmptyValue = false,
          paramType = "path", dataTypeClass = String.class,
          example = "1"),
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")})
  public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
    deleteSlide.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping(value = "/{id}", produces = {"application/json"})
  @ApiOperation(value = "Return the slide detail passed by id")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The slide's details.",
          response = ListCommentResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Slide not found.",
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
  public ResponseEntity<SlideResponse> getBy(@PathVariable(value = "id") Long id) {
    SlideResponse response = getSlideDetails.getBy(id);
    return ResponseEntity.ok().body(response);
  }

  @PutMapping(value = "/{id}",consumes = {"application/json"},
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Update a slide passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The Slide was successfully updated"),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Slide not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id", value = "Id of the slide we want to update",
          required = true, allowEmptyValue = false,
          paramType = "path", dataTypeClass = String.class,
          example = "1"),
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")})
  public ResponseEntity<Void> update(@PathVariable(value = "id") long id, @RequestBody
      UpdateSlideRequest updateSlideRequest) {
    updateSlide.update(updateSlideRequest, id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
