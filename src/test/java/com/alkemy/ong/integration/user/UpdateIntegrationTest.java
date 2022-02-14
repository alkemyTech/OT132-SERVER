package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
public class UpdateIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String FIELD_UPDATE = "EDITED";
  private final Long ID_TO_UPDATE = stubUser(RoleType.USER).getUserId();
  private final String PATH = "/users/" + ID_TO_UPDATE;


  @Test
  public void shouldReturnNotFoundWhenIdNoExist() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(ID_TO_UPDATE))).thenReturn(null);

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
    assertEquals("User not found.", response.getBody().getMessages().get(0));
  }

  @Test
  public void shouldUpdateUserWithAllFields() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(ID_TO_UPDATE))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        userUpdated(buildRequestPayLoad(), RoleType.USER));

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
    assertEquals(updateUserDetailsRequest.getFirstName(), userResponse.getFirstName());
    assertEquals(updateUserDetailsRequest.getLastName(), userResponse.getLastName());
    assertEquals(updateUserDetailsRequest.getEmail(), userResponse.getEmail());
    assertEquals(updateUserDetailsRequest.getPhoto(), userResponse.getPhoto());
  }

  @Test
  public void shouldUpdateUserWhenFirstNameIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(ID_TO_UPDATE))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        userUpdated(buildRequestPayLoadWithFirstNameNull(), RoleType.USER));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithFirstNameNull();

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotEquals(FIELD_UPDATE, response.getBody().getFirstName());
    assertEquals(updateUserDetailsRequest.getLastName(), response.getBody().getLastName());
    assertEquals(updateUserDetailsRequest.getEmail(), response.getBody().getEmail());
    assertEquals(updateUserDetailsRequest.getPhoto(), response.getBody().getPhoto());
  }

  @Test
  public void shouldUpdateUserWhenLastNameIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(ID_TO_UPDATE))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        userUpdated(buildRequestPayLoadWithLastNameNull(), RoleType.USER));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithLastNameNull();

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotEquals(FIELD_UPDATE, response.getBody().getLastName());
    assertEquals(updateUserDetailsRequest.getFirstName(), response.getBody().getFirstName());
    assertEquals(updateUserDetailsRequest.getEmail(), response.getBody().getEmail());
    assertEquals(updateUserDetailsRequest.getPhoto(), response.getBody().getPhoto());
  }

  @Test
  public void shouldUpdateUserWhenEmailIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(ID_TO_UPDATE))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        userUpdated(buildRequestPayLoadWithEmailNull(), RoleType.USER));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithEmailNull();

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotEquals(FIELD_UPDATE, response.getBody().getEmail());
    assertEquals(updateUserDetailsRequest.getFirstName(), response.getBody().getLastName());
    assertEquals(updateUserDetailsRequest.getLastName(), response.getBody().getLastName());
    assertEquals(updateUserDetailsRequest.getPhoto(), response.getBody().getPhoto());
  }

  @Test
  public void shouldUpdateUserWhenPasswordIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(ID_TO_UPDATE))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        userUpdated(buildRequestPayLoadWithPasswordNull(), RoleType.USER));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithPasswordNull();

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updateUserDetailsRequest.getFirstName(), response.getBody().getLastName());
    assertEquals(updateUserDetailsRequest.getLastName(), response.getBody().getLastName());
    assertEquals(updateUserDetailsRequest.getEmail(), response.getBody().getEmail());
    assertEquals(updateUserDetailsRequest.getPhoto(), response.getBody().getPhoto());
  }

  @Test
  public void shouldUpdateUserWhenPhotoIsNull() {
    when(userRepository.findByUserIdAndSoftDeleteFalse(eq(ID_TO_UPDATE))).thenReturn(
        stubUser(RoleType.USER));
    when(userRepository.save(any(User.class))).thenReturn(
        userUpdated(buildRequestPayLoadWithPhotoNull(), RoleType.USER));

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateUserDetailsRequest updateUserDetailsRequest = buildRequestPayLoadWithPhotoNull();

    HttpEntity<UpdateUserDetailsRequest> requestEntity = new HttpEntity<>(updateUserDetailsRequest,
        headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PATCH,
        requestEntity,
        UserResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotEquals(FIELD_UPDATE, response.getBody().getPhoto());
    assertEquals(updateUserDetailsRequest.getFirstName(), response.getBody().getLastName());
    assertEquals(updateUserDetailsRequest.getLastName(), response.getBody().getLastName());
    assertEquals(updateUserDetailsRequest.getEmail(), response.getBody().getEmail());

  }

  private User userUpdated(UpdateUserDetailsRequest request, RoleType role) {
    User userUpdated = stubUser(role);

    if (request.getFirstName() != null) {
      userUpdated.setFirstName(request.getFirstName());
    }
    if (request.getLastName() != null) {
      userUpdated.setLastName(request.getLastName());
    }
    if (request.getEmail() != null) {
      userUpdated.setEmail(request.getEmail());
    }
    if (request.getPassword() != null) {
      userUpdated.setPassword(request.getPassword());
    }
    if (request.getPhoto() != null) {
      userUpdated.setPhoto(request.getPhoto());
    }
    return userUpdated;
  }

  private UpdateUserDetailsRequest buildRequestPayLoad() {
    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    request.setFirstName(FIELD_UPDATE);
    request.setLastName(FIELD_UPDATE);
    request.setEmail(FIELD_UPDATE);
    request.setPassword(FIELD_UPDATE);
    request.setPhoto(FIELD_UPDATE);
    return request;
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithFirstNameNull() {
    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    request.setLastName(FIELD_UPDATE);
    request.setEmail(FIELD_UPDATE);
    request.setPassword(FIELD_UPDATE);
    request.setPhoto(FIELD_UPDATE);
    return request;
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithLastNameNull() {
    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    request.setFirstName(FIELD_UPDATE);
    request.setEmail(FIELD_UPDATE);
    request.setPassword(FIELD_UPDATE);
    request.setPhoto(FIELD_UPDATE);
    return request;
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithEmailNull() {
    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    request.setFirstName(FIELD_UPDATE);
    request.setLastName(FIELD_UPDATE);
    request.setPassword(FIELD_UPDATE);
    request.setPhoto(FIELD_UPDATE);
    return request;
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithPasswordNull() {
    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    request.setFirstName(FIELD_UPDATE);
    request.setLastName(FIELD_UPDATE);
    request.setEmail(FIELD_UPDATE);
    request.setPhoto(FIELD_UPDATE);
    return request;
  }

  private UpdateUserDetailsRequest buildRequestPayLoadWithPhotoNull() {
    UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
    request.setFirstName(FIELD_UPDATE);
    request.setLastName(FIELD_UPDATE);
    request.setEmail(FIELD_UPDATE);
    request.setPassword(FIELD_UPDATE);
    return request;
  }


}
