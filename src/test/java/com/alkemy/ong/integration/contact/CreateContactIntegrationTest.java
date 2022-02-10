package com.alkemy.ong.integration.contact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.mail.EmailUtils;
import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.integration.util.UtilMethods;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.request.CreateContactRequest;
import com.alkemy.ong.model.response.ContactResponse;
import com.alkemy.ong.repository.IContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateContactIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/contacts";
  private static final String NAME = "Bismark";
  private static final String EMAIL = "biskmark@gmail.com";
  private static final String INVALID_EMAIL = "@gmail.com";
  private static final int PHONE = 406203;
  private static final String MESSAGE = "Text random";

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
    buildBadRequest("Name cannot be empty or null.", createContactRequest);
  }

  @Test
  public void shouldReturnBadRequestWhenPhoneIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithEmptyPhone();
    buildBadRequest("Phone cannot be null.",
        createContactRequest);
  }

  @Test
  public void shouldReturnBadRequestWhenEmailIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithEmptyEmail();
    buildBadRequest("Email cannot be empty or null.",
        createContactRequest);
  }

  @Test
  public void shouldReturnBadRequestWhenMessageIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithEmptyMessage();
    buildBadRequest("Message cannot be empty or null.",
        createContactRequest);
  }

  @Test
  public void shouldReturnBadRequestWhenNameContainsMoreOf70Characters() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithMaxSizeName();
    buildBadRequest("Name can have between 2 and 70 characters.",
        createContactRequest);
  }

  @Test
  public void shouldReturnBadRequestWhenEmailContainsMoreOf150CharactersAndIsInvalid() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithMaxSizeEmail();

    HttpEntity<CreateContactRequest> requestEntity =
        new HttpEntity<>(createContactRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate
        .exchange(createURLWithPort(PATH),
            HttpMethod.POST,
            requestEntity,
            ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    ErrorResponse errorResponse = response.getBody();
    assertNotNull(errorResponse);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(2, errorResponse.getMessages().size());
    assertEquals("Email is not valid.", errorResponse.getMessages().get(0));
    assertEquals("Email need to have between 5 and 150 characters.",
        errorResponse.getMessages().get(1));
  }

  @Test
  public void shouldReturnBadRequestWhenEmailIsInvalid() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateContactRequest createContactRequest = buildRequestWithInvalidEmail();
    buildBadRequest("Email is not valid.", createContactRequest);
  }

  private CreateContactRequest buildRequestWithInvalidEmail() {
    return buildRequestPayLoad(NAME, PHONE, INVALID_EMAIL, MESSAGE);
  }

  private CreateContactRequest buildRequestWithMaxSizeEmail() {
    return buildRequestPayLoad(NAME, PHONE, UtilMethods.generateRandomString(152), MESSAGE);
  }

  private CreateContactRequest buildRequestWithMaxSizeName() {
    return buildRequestPayLoad(UtilMethods.generateRandomString(71), PHONE, EMAIL, MESSAGE);
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

  private void buildBadRequest(String message,
      CreateContactRequest createContactRequest) {

    HttpEntity<CreateContactRequest> requestEntity =
        new HttpEntity<>(createContactRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate
        .exchange(createURLWithPort(PATH),
            HttpMethod.POST,
            requestEntity,
            ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    ErrorResponse errorResponse = response.getBody();
    assertNotNull(errorResponse);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(1, errorResponse.getMessages().size());
    assertEquals(message, errorResponse.getMessages().get(0));
  }
}
