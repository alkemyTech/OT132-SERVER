package com.alkemy.ong.controller;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.response.ListUsersResponse;
import com.alkemy.ong.service.abstraction.IDeleteUser;
import com.alkemy.ong.service.abstraction.IGetUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IGetUserDetails getUserDetails;

  @Autowired
  IDeleteUser deleteUser;

  @GetMapping()
  public ResponseEntity<ListUsersResponse> list() {
    return ResponseEntity.ok().body(getUserDetails.findActiveUsers());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) throws NotFoundException {
    return ResponseEntity.ok().build();
  }
}
