package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UpdateUserDetailsRequest;
import com.alkemy.ong.model.response.UserResponse;
import java.text.MessageFormat;
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

  private static final String FIRST_NAME_UPDATED = "Bruce";
  private static final String LAST_NAME_UPDATED = "Wayne";
  private static final String EMAIL_UPDATED = "bruce@wayne.com";
  private static final String PHOTO_UPDATED = "http://www.s3.com/my-photo.jpg";
  private static final String PATH = "/users/" + USER_ID;

  private enum UserTestCasesAttributes {
    NULL_FIRST_NAME,
    NULL_LAST_NAME,
    NULL_EMAIL,
    NULL_PASSWORD,
    NULL_PHOTO
  }

  @Test
  public void shouldReturnNotFoundWhenUserDoesNotExist() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID))).thenReturn(null);

    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateUserDetailsRequest request = buildRequestPayLoad(RoleType.USER);
    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(request, headers);

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
  public void shouldReturnForbiddenWithNoRole() {
    UpdateUserDetailsRequest request = buildRequestPayLoad(RoleType.USER);
    ResponseEntity<UserResponse> response = executeSuccessRequest(request);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  @Test
  public void shouldUpdateUserWithAllFieldsWithUserRole() {
    mockUserRepository(RoleType.USER, buildStubUserUpdated(stubUser(RoleType.USER)));

    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateUserDetailsRequest request = buildRequestPayLoad(RoleType.USER);
    ResponseEntity<UserResponse> response = executeSuccessRequest(request);

    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertResponseBody(userResponse);
  }

  @Test
  public void shouldUpdateUserWithAllFieldsWithAdminRole() {
    mockUserRepository(RoleType.ADMIN, buildStubUserUpdated(stubUser(RoleType.ADMIN)));

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    UpdateUserDetailsRequest request = buildRequestPayLoad(RoleType.ADMIN);
    ResponseEntity<UserResponse> response = executeSuccessRequest(request);

    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertResponseBody(userResponse);
  }

  @Test
  public void shouldUpdateUserWhenFirstNameIsNull() {
    mockUserRepository(RoleType.USER,
        buildStubUserUpdated(stubUser(RoleType.USER), UserTestCasesAttributes.NULL_FIRST_NAME));

    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateUserDetailsRequest request =
        buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_FIRST_NAME);
    ResponseEntity<UserResponse> response = executeSuccessRequest(request);

    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertResponseBody(userResponse, UserTestCasesAttributes.NULL_FIRST_NAME);
  }

  @Test
  public void shouldUpdateUserWhenLastNameIsNull() {
    mockUserRepository(RoleType.USER,
        buildStubUserUpdated(stubUser(RoleType.USER), UserTestCasesAttributes.NULL_LAST_NAME));

    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateUserDetailsRequest request =
        buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_LAST_NAME);
    ResponseEntity<UserResponse> response = executeSuccessRequest(request);

    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertResponseBody(userResponse, UserTestCasesAttributes.NULL_LAST_NAME);
  }

  @Test
  public void shouldUpdateUserWhenEmailIsNull() {
    mockUserRepository(RoleType.USER,
        buildStubUserUpdated(stubUser(RoleType.USER), UserTestCasesAttributes.NULL_EMAIL));

    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateUserDetailsRequest request =
        buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_EMAIL);
    ResponseEntity<UserResponse> response = executeSuccessRequest(request);

    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertResponseBody(userResponse, UserTestCasesAttributes.NULL_EMAIL);
  }

  @Test
  public void shouldUpdateUserWhenPasswordIsNull() {
    mockUserRepository(RoleType.USER,
        buildStubUserUpdated(stubUser(RoleType.USER), UserTestCasesAttributes.NULL_PASSWORD));

    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateUserDetailsRequest request =
        buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_PASSWORD);
    ResponseEntity<UserResponse> response = executeSuccessRequest(request);

    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertResponseBody(userResponse, UserTestCasesAttributes.NULL_PASSWORD);
  }

  @Test
  public void shouldUpdateUserWhenPhotoIsNull() {
    mockUserRepository(RoleType.USER,
        buildStubUserUpdated(stubUser(RoleType.USER), UserTestCasesAttributes.NULL_PHOTO));

    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateUserDetailsRequest request =
        buildRequestPayLoad(RoleType.USER, UserTestCasesAttributes.NULL_PHOTO);
    ResponseEntity<UserResponse> response = executeSuccessRequest(request);

    UserResponse userResponse = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertResponseBody(userResponse, UserTestCasesAttributes.NULL_PHOTO);
  }

  private void mockUserRepository(RoleType roleType, User userUpdated) {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(USER_ID)))
        .thenReturn(stubUser(roleType));
    when(userRepository.save(any(User.class)))
        .thenReturn(userUpdated);
  }

  private User buildStubUserUpdated(User userUpdated,
      UserTestCasesAttributes... userTestCasesAttributes) {

    for (UserTestCasesAttributes testCaseAttribute : userTestCasesAttributes) {
      if (testCaseAttribute != UserTestCasesAttributes.NULL_FIRST_NAME) {
        userUpdated.setFirstName(FIRST_NAME_UPDATED);
      }

      if (testCaseAttribute != UserTestCasesAttributes.NULL_LAST_NAME) {
        userUpdated.setLastName(LAST_NAME_UPDATED);
      }

      if (testCaseAttribute != UserTestCasesAttributes.NULL_EMAIL) {
        userUpdated.setEmail(EMAIL_UPDATED);
      }

      if (testCaseAttribute != UserTestCasesAttributes.NULL_PHOTO) {
        userUpdated.setPhoto(PHOTO_UPDATED);
      }
    }
    return userUpdated;
  }

  private UpdateUserDetailsRequest buildRequestPayLoad(RoleType role,
      UserTestCasesAttributes... userTestCasesAttributes) {

    User userUpdated = stubUser(role);

    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    request.setFirstName(userUpdated.getFirstName());
    request.setLastName(userUpdated.getLastName());
    request.setEmail(userUpdated.getEmail());
    request.setPassword(userUpdated.getPassword());
    request.setPhoto(userUpdated.getPhoto());

    for (UserTestCasesAttributes testCaseAttribute : userTestCasesAttributes) {
      switch (testCaseAttribute) {
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
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("Test case: {0} is unsupported", testCaseAttribute));
      }
    }
    return request;
  }

  private void assertResponseBody(UserResponse response,
      UserTestCasesAttributes... userTestCasesAttributes) {

    for (UserTestCasesAttributes testCaseAttribute : userTestCasesAttributes) {

      if (testCaseAttribute == UserTestCasesAttributes.NULL_FIRST_NAME) {
        assertEquals(FIRST_NAME, response.getFirstName());
      } else {
        assertEquals(FIRST_NAME_UPDATED, response.getFirstName());
      }

      if (testCaseAttribute == UserTestCasesAttributes.NULL_LAST_NAME) {
        assertEquals(LAST_NAME, response.getLastName());
      } else {
        assertEquals(LAST_NAME_UPDATED, response.getLastName());
      }

      if (testCaseAttribute == UserTestCasesAttributes.NULL_EMAIL) {
        assertEquals(EMAIL, response.getEmail());
      } else {
        assertEquals(EMAIL_UPDATED, response.getEmail());
      }

      if (testCaseAttribute == UserTestCasesAttributes.NULL_PHOTO) {
        assertEquals(PHOTO, response.getPhoto());
      } else {
        assertEquals(PHOTO_UPDATED, response.getPhoto());
      }
    }
  }

  private ResponseEntity<UserResponse> executeSuccessRequest(UpdateUserDetailsRequest request) {
    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(request, headers);
    return restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
  }

}
