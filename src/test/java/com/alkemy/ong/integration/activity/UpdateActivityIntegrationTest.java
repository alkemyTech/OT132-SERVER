package com.alkemy.ong.integration.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.UpdateActivityRequest;
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
public class UpdateActivityIntegrationTest extends AbstractBaseActivityIntegrationTest {

  @Test
  public void shouldUpdateActivityWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(activityRepository.save(any(Activity.class))).thenReturn(createActivityStub());
    when(activityRepository.findByActivityIdAndSoftDeleteFalse(ACTIVITY_ID))
        .thenReturn(createActivityStub());

    UpdateActivityRequest request = buildRequestPayload();

    assertSuccessResponse(request);
  }

  @Test
  public void shouldUpdateActivityWithUserRole() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    when(activityRepository.save(any(Activity.class))).thenReturn(createActivityStub());
    when(activityRepository.findByActivityIdAndSoftDeleteFalse(ACTIVITY_ID))
        .thenReturn(createActivityStub());

    UpdateActivityRequest request = buildRequestPayload();

    assertSuccessResponse(request);
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithoutRole() {
    UpdateActivityRequest updateRequest = buildRequestPayload();
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(updateRequest);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnNotFoundWhenActivityDoesNotExistWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(activityRepository.findByActivityIdAndSoftDeleteFalse(ACTIVITY_ID)).thenReturn(null);

    UpdateActivityRequest request = buildRequestPayload();

    executeAndAssertObjectNotFoundRequest(request);
  }

  @Test
  public void shouldReturnNotFoundWhenActivityDoesNotExistWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    when(activityRepository.findByActivityIdAndSoftDeleteFalse(ACTIVITY_ID)).thenReturn(null);

    UpdateActivityRequest request = buildRequestPayload();

    executeAndAssertObjectNotFoundRequest(request);
  }

  @Test
  public void shouldReturnBadRequestWithEmptyNameAndRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    UpdateActivityRequest request = buildRequestWithEmptyName();

    executeAndAssertOneEmptyOrNullFieldInRequest(request, NAME_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWithEmptyNameAndRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateActivityRequest request = buildRequestWithEmptyName();

    executeAndAssertOneEmptyOrNullFieldInRequest(request, NAME_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenContentIsEmptyWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateActivityRequest request = buildRequestWithEmptyContent();

    executeAndAssertOneEmptyOrNullFieldInRequest(request, CONTENT_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenContentIsEmptyWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    UpdateActivityRequest request = buildRequestWithEmptyContent();

    executeAndAssertOneEmptyOrNullFieldInRequest(request, CONTENT_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmptyWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    UpdateActivityRequest request = buildRequestWithEmptyImage();

    executeAndAssertOneEmptyOrNullFieldInRequest(request, IMAGE_OBJ);
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmptyWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    UpdateActivityRequest request = buildRequestWithEmptyImage();

    executeAndAssertOneEmptyOrNullFieldInRequest(request, IMAGE_OBJ);
  }

  private UpdateActivityRequest buildRequestWithEmptyName() {
    return buildRequestPayload(null, CONTENT, IMAGE);
  }

  private UpdateActivityRequest buildRequestWithEmptyContent() {
    return buildRequestPayload(NAME, null, IMAGE);
  }

  private UpdateActivityRequest buildRequestWithEmptyImage() {
    return buildRequestPayload(NAME, CONTENT, null);
  }

  private UpdateActivityRequest buildRequestPayload() {
    return buildRequestPayload(NAME, CONTENT, IMAGE);
  }

  private UpdateActivityRequest buildRequestPayload(String name, String content, String image) {
    UpdateActivityRequest activityRequest = new UpdateActivityRequest();
    activityRequest.setName(name);
    activityRequest.setContent(content);
    activityRequest.setImage(image);
    return activityRequest;
  }

  private ResponseEntity<ErrorResponse> getErrorResponseEntity(
      UpdateActivityRequest updateRequest) {
    HttpEntity<UpdateActivityRequest> request = new HttpEntity<>(updateRequest, headers);

    return restTemplate.exchange(createURLWithPort(PATH_ID), HttpMethod.PUT, request,
        ErrorResponse.class);
  }

  private void assertSuccessResponse(UpdateActivityRequest request) {
    HttpEntity<UpdateActivityRequest> requestEntity = new HttpEntity<>(request, headers);
    ResponseEntity<ActivityResponse> response = restTemplate.exchange(createURLWithPort(PATH_ID),
        HttpMethod.PUT, requestEntity, ActivityResponse.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    ActivityResponse activityResponse = response.getBody();
    assertNotNull(activityResponse);
    assertEquals(request.getName(), activityResponse.getName());
    assertEquals(request.getContent(), activityResponse.getContent());
    assertEquals(request.getImage(), activityResponse.getImage());
  }

  public void executeAndAssertObjectNotFoundRequest(UpdateActivityRequest request) {
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(request);
    assertObjectNotFound(response, "Activity not found");
  }

  public void executeAndAssertOneEmptyOrNullFieldInRequest(UpdateActivityRequest request,
      String object) {
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(request);
    assertOneEmptyOrNullFieldInRequest(response, object + " cannot be empty or null.");
  }

}
