package com.alkemy.ong.integration.contact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.request.CreateContactRequest;
import com.alkemy.ong.model.response.ContactResponse;
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
public class CreateContactIntegrationTest extends AbstractBaseContactIntegrationTest {

  @Test
  public void shouldCreateContactWithRolUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestPayLoad();

    HttpEntity<CreateContactRequest> requestEntity =
        new HttpEntity<>(createContactRequest, headers);

    ResponseEntity<ContactResponse> response = restTemplate
        .exchange(createURLWithPort(PATH),
            HttpMethod.POST,
            requestEntity,
            ContactResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    ContactResponse contactResponse = response.getBody();
    assertNotNull(contactResponse);

    assertEquals(createContactRequest.getName(), contactResponse.getName());
    assertEquals(createContactRequest.getPhone(), contactResponse.getPhone());
    assertEquals(createContactRequest.getEmail(), contactResponse.getEmail());
    assertEquals(createContactRequest.getMessage(), contactResponse.getMessage());
  }

  @Test
  public void shouldReturnForbiddenWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    CreateContactRequest createContactRequest = buildRequestPayLoad();

    HttpEntity<CreateContactRequest> requestEntity =
        new HttpEntity<>(createContactRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate
        .exchange(createURLWithPort(PATH),
            HttpMethod.POST,
            requestEntity,
            ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    ErrorResponse errorResponse = response.getBody();
    assertNotNull(errorResponse);

    assertEquals(403, errorResponse.getStatus());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithEmptyName();
    createBadRequestAsserts(createRequestAndResponse(createContactRequest),
        "Name cannot be empty or null.");
  }

  @Test
  public void shouldReturnBadRequestWhenPhoneIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithEmptyPhone();
    createBadRequestAsserts(createRequestAndResponse(createContactRequest),
        "Phone cannot be null.");
  }

  @Test
  public void shouldReturnBadRequestWhenEmailIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithEmptyEmail();
    createBadRequestAsserts(createRequestAndResponse(createContactRequest),
        "Email cannot be empty or null.");
  }

  @Test
  public void shouldReturnBadRequestWhenMessageIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithEmptyMessage();
    createBadRequestAsserts(createRequestAndResponse(createContactRequest),
        "Message cannot be empty or null.");
  }

  @Test
  public void shouldReturnBadRequestWhenNameContainsMoreOf70Characters() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithMaxSizeName();
    createBadRequestAsserts(createRequestAndResponse(createContactRequest),
        "Name can have between 2 and 70 characters.");
  }

  @Test
  public void shouldReturnBadRequestWhenEmailContainsMoreOf150CharactersAndIsInvalid() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithMaxSizeEmail();

    ResponseEntity<ErrorResponse> response = createRequestAndResponse(createContactRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    ErrorResponse errorResponse = response.getBody();
    assertNotNull(errorResponse);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(2, errorResponse.getMessages().size());
  }

  private CreateContactRequest buildRequestWithMaxSizeEmail() {
    return buildRequestPayLoad(NAME, PHONE, generateRandomString(152), MESSAGE);
  }

  private CreateContactRequest buildRequestWithMaxSizeName() {
    return buildRequestPayLoad(generateRandomString(71), PHONE, EMAIL, MESSAGE);
  }

  private CreateContactRequest buildRequestWithEmptyPhone() {
    return buildRequestPayLoad(NAME, null, EMAIL,
        MESSAGE);
  }

  private CreateContactRequest buildRequestWithEmptyEmail() {
    return buildRequestPayLoad(NAME, PHONE, null, MESSAGE);
  }

  private CreateContactRequest buildRequestWithEmptyMessage() {
    return buildRequestPayLoad(NAME, PHONE, EMAIL, null);
  }

  private CreateContactRequest buildRequestWithEmptyName() {
    return buildRequestPayLoad(null, PHONE, EMAIL, MESSAGE);
  }

  private CreateContactRequest buildRequestPayLoad() {
    return buildRequestPayLoad(NAME, PHONE, EMAIL, MESSAGE);
  }

  private CreateContactRequest buildRequestPayLoad(String name, Integer phone, String email,
      String message) {
    CreateContactRequest createContactRequest = new CreateContactRequest();
    createContactRequest.setName(name);
    createContactRequest.setPhone(phone);
    createContactRequest.setEmail(email);
    createContactRequest.setMessage(message);
    return createContactRequest;
  }

  private ResponseEntity<ErrorResponse> createRequestAndResponse(
      CreateContactRequest createContactRequest) {

    HttpEntity<CreateContactRequest> requestEntity =
        new HttpEntity<>(createContactRequest, headers);

    return restTemplate
        .exchange(createURLWithPort(PATH),
            HttpMethod.POST,
            requestEntity,
            ErrorResponse.class);
  }

  private void createBadRequestAsserts(ResponseEntity<ErrorResponse> response, String message) {

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    ErrorResponse errorResponse = response.getBody();
    assertNotNull(errorResponse);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(1, errorResponse.getMessages().size());
    assertEquals(message, errorResponse.getMessages().get(0));
  }
}