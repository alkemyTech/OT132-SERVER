package com.alkemy.ong.integration.activities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.CreateActivityRequest;
import com.alkemy.ong.model.response.ActivityResponse;
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
public class CreateActivityIntegrationTest extends AbstractBaseActivityIntegrationTest {

  @Test
  public void shouldCreateActivityWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(activityRepository.save(any(Activity.class))).thenReturn(createActivityStub());
    CreateActivityRequest request = buildRequestPayload();

    assertOneEmptyOrNullFieldInRequest(request);
  }

  @Test
  public void shouldCreateActivityWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    when(activityRepository.save(any(Activity.class))).thenReturn(createActivityStub());
    CreateActivityRequest request = buildRequestPayload();

    assertOneEmptyOrNullFieldInRequest(request);
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithoutRole() {
    CreateActivityRequest createRequest = buildRequestPayload();
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createRequest);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmptyWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    CreateActivityRequest request = buildRequestWithEmptyName();

    assertObjectNotFound(request, NAME_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmptyWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    CreateActivityRequest request = buildRequestWithEmptyName();

    assertObjectNotFound(request, NAME_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenContentIsEmptyWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    CreateActivityRequest request = buildRequestWithEmptyContent();

    assertObjectNotFound(request, CONTENT_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenContentIsEmptyWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    CreateActivityRequest request = buildRequestWithEmptyContent();

    assertObjectNotFound(request, CONTENT_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmptyWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    CreateActivityRequest request = buildRequestWithEmptyImage();

    assertObjectNotFound(request, IMAGE_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmptyWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    CreateActivityRequest request = buildRequestWithEmptyImage();

    assertObjectNotFound(request, IMAGE_OBJ);
  }

  private CreateActivityRequest buildRequestWithEmptyName() {
    return buildRequestPayload(null, CONTENT, IMAGE);
  }

  private CreateActivityRequest buildRequestWithEmptyContent() {
    return buildRequestPayload(NAME, null, IMAGE);
  }

  private CreateActivityRequest buildRequestWithEmptyImage() {
    return buildRequestPayload(NAME, CONTENT, null);
  }

  private CreateActivityRequest buildRequestPayload() {
    return buildRequestPayload(NAME, CONTENT, IMAGE);
  }

  private CreateActivityRequest buildRequestPayload(String name, String content, String image) {
    CreateActivityRequest activityRequest = new CreateActivityRequest();
    activityRequest.setName(name);
    activityRequest.setContent(content);
    activityRequest.setImage(image);
    return activityRequest;
  }

  private ResponseEntity<ErrorResponse> getErrorResponseEntity(
      CreateActivityRequest createRequest) {
    HttpEntity<CreateActivityRequest> request = new HttpEntity<>(createRequest, headers);

    return restTemplate.exchange(createURLWithPort(PATH), HttpMethod.POST, request,
        ErrorResponse.class);
  }

  public void assertOneEmptyOrNullFieldInRequest(CreateActivityRequest request) {

    HttpEntity<CreateActivityRequest> requestEntity = new HttpEntity<>(request, headers);
    ResponseEntity<ActivityResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ActivityResponse.class);

    ActivityResponse activityResponse = response.getBody();

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(activityResponse);
    assertEquals(request.getName(), activityResponse.getName());
    assertEquals(request.getContent(), activityResponse.getContent());
    assertEquals(request.getImage(), activityResponse.getImage());
  }

  private void assertObjectNotFound(CreateActivityRequest request, String object) {
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(request);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(400, getStatusValue(response));
    assertEquals(object + " cannot be empty or null.", getFirstMessageError(response));
  }

}
