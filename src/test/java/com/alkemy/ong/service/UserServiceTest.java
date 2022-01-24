package com.alkemy.ong.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  private static final String USERNAME = "test-user@test.com";

  @Mock
  private IUserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  public void shouldReturnUserDetailsWhenUsernameExist() {
    when(userRepository.findByEmail(eq(USERNAME))).thenReturn(stubUser());

    UserDetails userDetails = userService.loadUserByUsername(USERNAME);
    assertEquals(USERNAME, userDetails.getUsername());
  }

  @Test(expected = UsernameNotFoundException.class)
  public void shouldThrowExceptionWhenUsernameNotExist() {
    when(userRepository.findByEmail(eq(USERNAME))).thenReturn(null);

    userService.loadUserByUsername(USERNAME);
  }

  private User stubUser() {
    User user = new User();
    user.setEmail(USERNAME);
    return user;
  }

}
