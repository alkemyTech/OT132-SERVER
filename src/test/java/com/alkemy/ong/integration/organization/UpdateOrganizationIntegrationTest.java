package com.alkemy.ong.integration.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.request.UpdateOrganizationRequest;
import com.alkemy.ong.model.response.OrganizationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateOrganizationIntegrationTest extends AbstractBaseOrganizationIntegrationTest {

  @Test
  public void shouldUpdateOrganizationWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(organizationRepository.save(any(Organization.class))).thenReturn(createOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayload();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<OrganizationResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        OrganizationResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    OrganizationResponse organizationResponse = response.getBody();
    assertNotNull(organizationResponse);
    assertEquals(updateOrganizationRequest.getName(), organizationResponse.getName());
    assertEquals(updateOrganizationRequest.getEmail(), organizationResponse.getEmail());
    assertEquals(updateOrganizationRequest.getImage(), organizationResponse.getImage());
    assertEquals(updateOrganizationRequest.getWelcomeText(), organizationResponse.getWelcomeText());
  }

  @Test
  public void shouldReturnForbiddenWithUserRole() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayload();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(0, getAmountMessages(response));
  }

  @Test
  public void shouldReturnForbiddenWithNoRole() {

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayload();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(0, getAmountMessages(response));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayloadWithEmptyName();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(1, getAmountMessages(response));
    assertEquals("Name field can not be null or empty.", getFirstMessageError(response));
  }

  @Test
  public void shouldReturnBadRequestWhenEmailIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayloadWithEmptyEmail();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(1, getAmountMessages(response));
    assertEquals("Email field can not be null or empty.", getFirstMessageError(response));
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayloadWithEmptyImage();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(1, getAmountMessages(response));
    assertEquals("Image field can not be null or empty.", getFirstMessageError(response));
  }

  @Test
  public void shouldReturnBadRequestWhenWelcomeTextIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayloadWithEmptyWelcomeText();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(1, getAmountMessages(response));
    assertEquals("Welcome text field can not be null or empty.",
        getFirstMessageError(response));
  }

  private UpdateOrganizationRequest buildRequestPayloadWithEmptyName() {
    return buildRequestPayload(null, IMAGE, EMAIL, WELCOME_TEXT);
  }

  private UpdateOrganizationRequest buildRequestPayloadWithEmptyImage() {
    return buildRequestPayload(NAME, null, EMAIL, WELCOME_TEXT);
  }

  private UpdateOrganizationRequest buildRequestPayloadWithEmptyEmail() {
    return buildRequestPayload(NAME, IMAGE, null, WELCOME_TEXT);
  }

  private UpdateOrganizationRequest buildRequestPayloadWithEmptyWelcomeText() {
    return buildRequestPayload(NAME, IMAGE, EMAIL, null);
  }

  private UpdateOrganizationRequest buildRequestPayload() {
    return buildRequestPayload(NAME, IMAGE, EMAIL, WELCOME_TEXT);
  }

  private UpdateOrganizationRequest buildRequestPayload(String name, String image, String email,
      String welcomeText) {
    UpdateOrganizationRequest request = new UpdateOrganizationRequest();
    request.setName(name);
    request.setImage(image);
    request.setEmail(email);
    request.setWelcomeText(welcomeText);
    request.setPhone(PHONE);
    request.setAddress(ADDRESS);
    return request;
  }

}
