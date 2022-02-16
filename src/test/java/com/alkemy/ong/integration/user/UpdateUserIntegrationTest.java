package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UpdateUserDetailsRequest;
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
public class UpdateUserIntegrationTest extends AbstractBaseIntegrationTest {

  private final String PATH = "/users/" + USER_ID;

  @Test
  public void shouldReturnNotFoundWhenUserDoesNotExist() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(null);

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.USER);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("User not found.", response.getBody().getMessages().get(0));
  }

  @Test
  public void shouldUpdateUserWithAllFieldsWithUserRole() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.USER);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    UserResponse userResponse = response.getBody();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWithAllFieldsWithAdminRole() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.ADMIN));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.ADMIN));

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.ADMIN);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldReturnForbiddenWithNoRole() {
    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.USER);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  @Test
  public void shouldUpdateUserWhenFirstNameIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserTestCasesAttributes.NULL_FIRST_NAME));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.USER,UserTestCasesAttributes.NULL_FIRST_NAME);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWhenLastNameIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserTestCasesAttributes.NULL_LAST_NAME));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_LAST_NAME);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWhenEmailIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserTestCasesAttributes.NULL_EMAIL));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_EMAIL);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertNotEquals(EMAIL, response.getBody().getEmail());
    assertTrue(assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWhenPasswordIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserTestCasesAttributes.NULL_PASSWORD));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_PASSWORD);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWhenPhotoIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserTestCasesAttributes.NULL_PHOTO));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_PHOTO);

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(assertResponseBody(updateUserDetailsRequest, userResponse));  }

  private User buildStubUserUpdated(RoleType role,
      UserTestCasesAttributes... userTestCasesAttributes) {

    User userUpdated = stubUser(role);

    for (UserTestCasesAttributes userAttribute : userTestCasesAttributes) {
      switch (userAttribute) {
        case NULL_FIRST_NAME:
          userUpdated.setFirstName(null);
          break;
        case NULL_LAST_NAME:
          userUpdated.setLastName(null);
          break;
        case NULL_EMAIL:
          userUpdated.setEmail(null);
          break;
        case NULL_PASSWORD:
          userUpdated.setPassword(null);
          break;
        case NULL_PHOTO:
          userUpdated.setPhoto(null);
          break;
      }
    }
    return userUpdated;
  }

  private UpdateUserDetailsRequest buildRequestPayLoad(RoleType role,
      UserTestCasesAttributes... userTestCasesAttributes) {

    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    User userUpdated = stubUser(role);

    request.setFirstName(userUpdated.getFirstName());
    request.setLastName(userUpdated.getLastName());
    request.setEmail(userUpdated.getEmail());
    request.setPassword(userUpdated.getPassword());
    request.setPhoto(userUpdated.getPhoto());

    for (UserTestCasesAttributes userAttribute : userTestCasesAttributes) {
      switch (userAttribute) {
        case NULL_FIRST_NAME:
          request.setFirstName(null);
          break;
        case NULL_LAST_NAME:
          request.setLastName(null);
          break;
        case NULL_EMAIL:
          request.setEmail(null);
          break;
        case NULL_PASSWORD:
          request.setPassword(null);
          break;
        case NULL_PHOTO:
          request.setPhoto(null);
          break;
      }
    }
    return request;
  }

  private Boolean assertResponseBody(UpdateUserDetailsRequest request, UserResponse response) {
    if (request.getFirstName() != null) {
      if (!request.getFirstName().equals(response.getFirstName())) {
        return false;
      }
    }
    if (request.getLastName() != null) {
      if (!request.getLastName().equals(response.getLastName())) {
        return false;
      }
    }
    if (request.getEmail() != null) {
      if (!request.getEmail().equals(response.getEmail())) {
        return false;
      }
    }
    if (request.getPhoto() != null) {
      if (!request.getPhoto().equals(response.getPhoto())) {
        return false;
      }
    }
    return true;
  }

}
