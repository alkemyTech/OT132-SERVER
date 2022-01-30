package com.alkemy.ong.controller;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.response.AuthenticationResponse;
import com.alkemy.ong.service.abstraction.ILoginService;
import javax.naming.AuthenticationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  @Autowired
  private ILoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authenticationRequest)
      throws InvalidCredentialsException{
    AuthenticationResponse user = loginService.login(authenticationRequest);
    return ResponseEntity.ok(user);
  }

}
