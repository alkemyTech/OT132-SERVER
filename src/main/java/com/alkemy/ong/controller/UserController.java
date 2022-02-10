package com.alkemy.ong.controller;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UpdateUserDetailsRequest;
import com.alkemy.ong.model.response.ListUsersResponse;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.service.abstraction.IDeleteUser;
import com.alkemy.ong.service.abstraction.IGetUserDetails;
import com.alkemy.ong.service.abstraction.IUpdateUserDetails;
import javax.validation.Valid;
import net.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IGetUserDetails getUserDetails;

  @Autowired
  private IDeleteUser deleteUser;

  @Autowired
  private IUpdateUserDetails updateUserDetails;

  @GetMapping()
  public ResponseEntity<ListUsersResponse> list() {
    return ResponseEntity.ok().body(getUserDetails.findActiveUsers());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
    deleteUser.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid
      UpdateUserDetailsRequest updateUserDetailsRequest) throws NotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(updateUserDetails.update(updateUserDetailsRequest, id));
  }
}
