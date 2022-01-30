package com.alkemy.ong.controller;

import com.alkemy.ong.exception.UserRegisterException;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserRegisterResponse;
import com.alkemy.ong.service.abstraction.IRegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  @Autowired
  private IRegisterUserService iRegisterUserService;

  @PostMapping("/register")
  public ResponseEntity<UserRegisterResponse> getUserRegister(@RequestBody UserRegisterRequest user)
      throws UserRegisterException {

    UserRegisterResponse userRegisterResponse = iRegisterUserService.dataRegister(user);
    return ResponseEntity.ok().body(userRegisterResponse);

  }


}
