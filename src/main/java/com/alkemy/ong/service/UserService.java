package com.alkemy.ong.service;

import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.request.UpdateUserDetailsRequest;
import com.alkemy.ong.model.response.AuthenticationResponse;
import com.alkemy.ong.model.response.ListUsersResponse;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IDeleteUser;
import com.alkemy.ong.service.abstraction.IGetUserDetails;
import com.alkemy.ong.service.abstraction.ILogin;
import com.alkemy.ong.service.abstraction.IUpdateUserDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, IGetUserDetails, ILogin, IDeleteUser,
    IUpdateUserDetails {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtils jwtUtil;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUser(username);
  }

  @Override
  public ListUsersResponse findActiveUsers() {
    List<User> users = userRepository.findBySoftDeleteFalse();
    return buildListResponse(users);
  }

  @Override
  public AuthenticationResponse login(AuthenticationRequest authenticationRequest)
      throws InvalidCredentialsException {
    User user = userRepository.findByEmail(authenticationRequest.getEmail());
    if (user == null) {
      throw new InvalidCredentialsException("Invalid email or password.");
    }

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
            authenticationRequest.getPassword()));

    return new AuthenticationResponse(user.getEmail(), jwtUtil.generateToken(user));
  }

  @Override
  public void delete(Long id) {
    User user = userRepository.findByUserIdAndSoftDeleteFalse(id);
    if (user == null || user.isSoftDelete()) {
      throw new NotFoundException("User not found");
    }
    user.setSoftDelete(true);
    userRepository.save(user);
  }

  @Override
  public UserResponse findBy(String username) throws UsernameNotFoundException {
    return userMapper.map(getUser(username));
  }

  private User getUser(String username) {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
    }
    return user;
  }

  private ListUsersResponse buildListResponse(List<User> users) {
    List<UserResponse> userResponses = userMapper.map(users);
    ListUsersResponse listUsersResponse = new ListUsersResponse();
    listUsersResponse.setUserResponses(userResponses);
    return listUsersResponse;
  }

  @Override
  public UserResponse update(UpdateUserDetailsRequest updateUserDetailsRequest, Long userId) {
    User user = userRepository.findByUserIdAndSoftDeleteFalse(userId);

    if (user == null) {
      throw new NotFoundException(USER_NOT_FOUND_MESSAGE);
    }

    if (updateUserDetailsRequest.getFirstName() != null) {
      user.setFirstName(updateUserDetailsRequest.getFirstName());
    }
    if (updateUserDetailsRequest.getLastName() != null) {
      user.setLastName(updateUserDetailsRequest.getLastName());
    }
    if (updateUserDetailsRequest.getEmail() != null) {
      user.setEmail(updateUserDetailsRequest.getEmail());
    }
    if (updateUserDetailsRequest.getPassword() != null) {
      user.setPassword(updateUserDetailsRequest.getPassword());
    }
    if (updateUserDetailsRequest.getPhoto() != null) {
      user.setLastName(updateUserDetailsRequest.getPhoto());
    }

    userRepository.save(user);

    return userMapper.map(user);
  }
}
