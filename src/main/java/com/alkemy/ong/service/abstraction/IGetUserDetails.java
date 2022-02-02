package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ListUsersResponse;
import com.alkemy.ong.model.response.UserResponse;

public interface IGetUserDetails {

  ListUsersResponse findActiveUsers();

  UserResponse findBy(String username);

  UserResponse findAuthenticatedUser(String authentication);

}
