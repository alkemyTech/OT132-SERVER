package com.alkemy.ong.controller;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.UserRegisterException;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.AuthenticationResponse;
import com.alkemy.ong.model.response.UserRegisterResponse;
import com.alkemy.ong.service.abstraction.ILoginService;
import com.alkemy.ong.service.abstraction.IRegisterUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  @Autowired
  private IRegisterUserService userService;

  @Autowired
  private ILoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authenticationRequest)
      throws InvalidCredentialsException {
    return ResponseEntity.ok(loginService.login(authenticationRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest user)
      throws UserRegisterException {
    UserRegisterResponse response = userService.dataRegister(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
