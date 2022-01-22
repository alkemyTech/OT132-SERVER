package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  @Autowired
  private IUserRepository userRepository;

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

}
