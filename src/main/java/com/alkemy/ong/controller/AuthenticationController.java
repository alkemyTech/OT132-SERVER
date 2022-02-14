package com.alkemy.ong.controller;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.exception.defaulterrors.Error400;
import com.alkemy.ong.exception.defaulterrors.Error403;
import com.alkemy.ong.exception.defaulterrors.Error404;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.AuthenticationResponse;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.service.abstraction.IGetUserDetails;
import com.alkemy.ong.service.abstraction.ILogin;
import com.alkemy.ong.service.abstraction.IRegisterUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.security.Principal;
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
@RequestMapping("auth")
@Api(tags = "Authentication Endpoints", value = "AuthenticationEndpoints")
public class AuthenticationController {

  @Autowired
  private IRegisterUser registerUser;

  @Autowired
  private ILogin login;

  @Autowired
  private IGetUserDetails getUserDetails;

  @PostMapping(value = "/login",produces = {"application/json"},
      consumes = {"application/json"})
  @ApiOperation(value = "Log a new user to the API", produces = "application/json",
                consumes = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "User logged in", response = AuthenticationResponse.class),
      @ApiResponse(code = 403, message = "Forbidden.",response = Error403.class),
      @ApiResponse(code = 400, message = "Bad Request.", response = Error400.class)})
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authenticationRequest)
      throws InvalidCredentialsException {
    return ResponseEntity.ok(login.login(authenticationRequest));
  }

  @PostMapping(value = "/register",produces = {"application/json"},
      consumes = {"application/json"})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Register an user and get the bearer token", produces = "application/json",
                consumes = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "User register successfully",
          response = AuthenticationResponse.class),
      @ApiResponse(code = 403, message = "Forbidden - Invalid mail o password",
          response = Error403.class),
      @ApiResponse(code = 400, message = "Bad request - Mail cannot be null or empty."
                                       + "or password cannot be null or empty. ",
          response = Error400.class)})
  public ResponseEntity<UserResponse> register(
      @RequestBody @Valid UserRegisterRequest userRegisterRequest)
      throws UserAlreadyExistException {

    UserResponse response = registerUser.register(userRegisterRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping(value = "/me",produces = {"application/json"})
  @ApiOperation(value = "Get my user details", produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "User Details", response = UserResponse.class),
      @ApiResponse(code = 403, message = "Bad credentials.", response = Error403.class),
      @ApiResponse(code = 404, message = "Bad request.", response = Error404.class)})
  public ResponseEntity<UserResponse> getUserDetails(Principal principal) {
    return ResponseEntity.ok(getUserDetails.findBy(principal.getName()));
  }
}
