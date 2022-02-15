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
import com.amazonaws.services.dynamodbv2.xspec.L;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class UpdateIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String FIRST_NAME = "FIRST_NAME_EDITED";
  private static final String LAST_NAME = "LAST_NAME_EDITED";
  private static final String EMAIL = "EMAIL_EDITED";
  private static final String PASSWORD = "PASSWORD_EDITED";
  private static final String PHOTO = "PHOTO_EDITED";

  private final String PATH = "/users/" + USER_ID;


  @Test
  public void shouldReturnNotFoundWhenIdNoExist() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(null);

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad();

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

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad();

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
    assertEquals("true", assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWithAllFieldsWithAdminRole() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.ADMIN));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.ADMIN));

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad();

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
    assertEquals("true", assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldReturnForbiddenWithNoRole() {
    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoad();

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
        buildStubUserUpdated(RoleType.USER, UserAttributes.NULL_FIRST_NAME));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithFirstNameNull();

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
    assertNotEquals(FIRST_NAME, response.getBody().getFirstName());
    assertEquals("true", assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWhenLastNameIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserAttributes.NULL_LAST_NAME));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithLastNameNull();

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
    assertNotEquals(LAST_NAME, response.getBody().getLastName());
    assertEquals("true", assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWhenEmailIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserAttributes.NULL_EMAIL));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithEmailNull();

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
    assertEquals("true", assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWhenPasswordIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserAttributes.NULL_PASSWORD));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithPasswordNull();

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
    assertEquals("true", assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  @Test
  public void shouldUpdateUserWhenPhotoIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        buildStubUserUpdated(RoleType.USER, UserAttributes.NULL_PHOTO));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithPhotoNull();

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
    assertNotEquals(PHOTO, response.getBody().getPhoto());
    assertEquals("true", assertResponseBody(updateUserDetailsRequest, userResponse));
  }

  private User buildStubUserUpdated(RoleType role,
      UserAttributes... userAttributes) {

    User userUpdated = stubUser(role);

    userUpdated.setFirstName(FIRST_NAME);
    userUpdated.setLastName(LAST_NAME);
    userUpdated.setEmail(EMAIL);
    userUpdated.setPassword(PASSWORD);
    userUpdated.setPhoto(PHOTO);

    for (UserAttributes userAttribute : userAttributes) {
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

  private UpdateUserDetailsRequest buildRequestPayLoad() {
    return buildRequestPayLoad(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO);
  }

  private UpdateUserDetailsRequest buildRequestPayLoad(String firstName, String lastName,
      String email, String password, String photo) {
    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    request.setFirstName(firstName);
    request.setLastName(lastName);
    request.setEmail(email);
    request.setPassword(password);
    request.setPhoto(photo);
    return request;
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithFirstNameNull() {
    return buildRequestPayLoad(null, LAST_NAME, EMAIL, PASSWORD, PHOTO);
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithLastNameNull() {
    return buildRequestPayLoad(FIRST_NAME, null, EMAIL, PASSWORD, PHOTO);
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithEmailNull() {
    return buildRequestPayLoad(FIRST_NAME, LAST_NAME, null, PASSWORD, PHOTO);
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithPasswordNull() {
    return buildRequestPayLoad(FIRST_NAME, LAST_NAME, EMAIL, null, PHOTO);
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithPhotoNull() {
    return buildRequestPayLoad(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, null);
  }

  private String assertResponseBody(UpdateUserDetailsRequest request, UserResponse response) {
    if (request.getFirstName() != null) {
      if (!request.getFirstName().equals(response.getFirstName())) {
        return "false";
      }
    }
    if (request.getLastName() != null) {
      if (!request.getLastName().equals(response.getLastName())) {
        return "false";
      }
    }
    if (request.getEmail() != null) {
      if (!request.getEmail().equals(response.getEmail())) {
        return "false";
      }
    }
    if (request.getPhoto() != null) {
      if (!request.getPhoto().equals(response.getPhoto())) {
        return "false";
      }
    }
    return "true";
  }

}
