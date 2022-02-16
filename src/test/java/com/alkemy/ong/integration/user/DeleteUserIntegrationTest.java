package com.alkemy.ong.integration.user;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.response.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DeleteUserIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/users/" + USER_ID;

  @Test
  public void shouldReturnNotFoundWhenUserDoesNotExist() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(null);

    setAuthorizationHeaderBasedOn(RoleType.USER);

    HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.DELETE,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("User not found", response.getBody().getMessages().get(0));
  }

  @Test
  public void shouldSoftDeleteUserSuccessFullyWithUserRole() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    HttpEntity<UserResponse> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.DELETE,
        requestEntity,
        UserResponse.class);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void shouldSoftDeleteUserSuccessFullyWithAdminRole() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.ADMIN));

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    HttpEntity<UserResponse> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.DELETE,
        requestEntity,
        UserResponse.class);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void shouldReturnForbiddenWithNoRole() {
    HttpEntity<UserResponse> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.DELETE,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

}
