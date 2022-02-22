package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.UpdateUserDetailsRequest;
import com.alkemy.ong.model.response.ListUsersResponse;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.service.abstraction.IDeleteUser;
import com.alkemy.ong.service.abstraction.IGetUserDetails;
import com.alkemy.ong.service.abstraction.IUpdateUserDetails;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api(tags = "User Endpoints", value = "UserEndpoints")
public class UserController {

  @Autowired
  private IGetUserDetails getUserDetails;

  @Autowired
  private IDeleteUser deleteUser;

  @Autowired
  private IUpdateUserDetails updateUserDetails;

  @GetMapping(produces = {"application/json"})
  @ApiOperation(value = "Return the list of users")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The list of users.",
          response = ListUsersResponse.class),
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
  public ResponseEntity<ListUsersResponse> list() {
    return ResponseEntity.ok().body(getUserDetails.findActiveUsers());
  }

  @DeleteMapping(value = "/{id}", produces = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete an user passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The user was successfully deleted"),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - User not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the user we want to delete",
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
    deleteUser.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PatchMapping(value = "/{id}", consumes = {"application/json"},
      produces = {"application/json"})
  @ApiOperation(value = "Update an user passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The user was successfully updated",
          response = UserResponse.class),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - User not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id", value = "Id of the user we want to update",
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
  public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid
      UpdateUserDetailsRequest updateUserDetailsRequest) throws NotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(updateUserDetails.update(updateUserDetailsRequest, id));
  }
}
