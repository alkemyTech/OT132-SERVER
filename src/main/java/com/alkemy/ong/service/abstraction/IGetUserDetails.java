package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ListUsersResponse;
import com.alkemy.ong.model.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IGetUserDetails {

  ListUsersResponse findActiveUsers();

  UserResponse findBy(String username) throws UsernameNotFoundException;

}
