package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.request.CreateActivityRequest;
import com.alkemy.ong.model.request.UpdateActivityRequest;
import com.alkemy.ong.model.response.ActivityResponse;
import com.alkemy.ong.service.abstraction.ICreateActivity;
import com.alkemy.ong.service.abstraction.IUpdateActivity;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activities")
@Api(tags = "Activity Endpoints", value = "ActivityEndpoints")
public class ActivityController {

  @Autowired
  private ICreateActivity createActivity;

  @Autowired
  private IUpdateActivity updateActivity;

  @PostMapping(consumes = {"application/json"},
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create an activity")
  @ApiResponses(value = {
      @ApiResponse(code = 201,
          message = "CREATED - The activity was successfully created.",
          response = ActivityResponse.class),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)
  })
  @ApiImplicitParam(name = "Authorization", value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public ResponseEntity<ActivityResponse> create(
      @RequestBody @Valid CreateActivityRequest createActivityRequest) {
    ActivityResponse activityResponse = createActivity.create(createActivityRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(activityResponse);
  }

  @PutMapping(value = "/{id}",produces = {"application/json"},
      consumes = {"application/json"})
  @ApiOperation(value = "Update a member passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The activity was successfully updated.",
          response = ActivityResponse.class),
      @ApiResponse(code = 400,message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null."),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Activity not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id", value = "Id of the member we want to update",
          required = true, allowEmptyValue = false, paramType = "path",
          dataTypeClass = String.class, example = "1"),
      @ApiImplicitParam(name = "Authorization", value = "Access Token",
          required = true, allowEmptyValue = false, paramType = "header",
          dataTypeClass = String.class, example = "Bearer access_token")})
  public ResponseEntity<ActivityResponse> update(@PathVariable long id,
      @Valid @RequestBody UpdateActivityRequest updateActivityRequest) {
    return ResponseEntity.ok(updateActivity.update(id, updateActivityRequest));
  }

}
