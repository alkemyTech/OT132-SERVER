package com.alkemy.ong.service;

import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.UserListResponse;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IGetUserDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, IGetUserDetails {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUser(username);
  }

  private User getUser(String username) {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
    }
    return user;
  }

  @Override
  public UserListResponse findAll() {
    List<User> users = userRepository.findBySoftDeleteFalse();
    return buildListResponse(users);
  }

  private UserListResponse buildListResponse(List<User> users) {
    List<UserResponse> userResponses = userMapper.mapToList(users);
    UserListResponse userListResponse = new UserListResponse();
    userListResponse.setUserResponses(userResponses);
    return userListResponse;
  }

}
