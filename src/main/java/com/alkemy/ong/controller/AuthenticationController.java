package com.alkemy.ong.controller;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.AuthenticationResponse;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.service.abstraction.IGetUserDetails;
import com.alkemy.ong.service.abstraction.ILogin;
import com.alkemy.ong.service.abstraction.IRegisterUser;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  @Autowired
  private IRegisterUser registerUser;

  @Autowired
  private ILogin login;

  @Autowired
  private IGetUserDetails getUserDetails;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authenticationRequest)
      throws InvalidCredentialsException {
    return ResponseEntity.ok(login.login(authenticationRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(
      @RequestBody @Valid UserRegisterRequest userRegisterRequest)
      throws UserAlreadyExistException {
    UserResponse response = registerUser.register(userRegisterRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getUserDetails(Principal user) {
    UserResponse userResponse = getUserDetails.findBy(user.getName());
    return ResponseEntity.ok(userResponse);
  }

  @GetMapping("/me2")
  public ResponseEntity<UserResponse> getUserDetails(@RequestHeader ("Authorization") String auth) {   
    return ResponseEntity.ok(getUserDetails.findAuthenticatedUser(auth));    
  }
}
