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
import com.alkemy.ong.repository.IOrganizationRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateOrganizationIntegrationTest extends AbstractBaseOrganizationIntegrationTest {



  @MockBean
  private IOrganizationRepository organizationRepository;

  @Test
  public void shouldUpdateOrganizationWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());
    when(organizationRepository.save(any(Organization.class))).thenReturn(createOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayload();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<OrganizationResponse> response = restTemplate.exchange(
        createURLWithPort(PATH), HttpMethod.POST, requestEntity, OrganizationResponse.class);

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

    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayload();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(0, response.getBody().getMessages().size());
  }

  @Test
  public void shouldReturnForbiddenWithNoRole() {
    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayload();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(0, response.getBody().getMessages().size());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayloadWithEmptyName();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(1, response.getBody().getMessages().size());
    assertEquals("Name field can not be null or empty.", response.getBody().getMessages().get(0));
  }

  @Test
  public void shouldReturnBadRequestWhenEmailIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayloadWithEmptyEmail();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(1, response.getBody().getMessages().size());
    assertEquals("Email field can not be null or empty.", response.getBody().getMessages().get(0));
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayloadWithEmptyImage();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(1, response.getBody().getMessages().size());
    assertEquals("Image field can not be null or empty.", response.getBody().getMessages().get(0));
  }

  @Test
  public void shouldReturnBadRequestWhenWelcomeTextIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayloadWithEmptyWelcomeText();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(1, response.getBody().getMessages().size());
    assertEquals("Welcome text field can not be null or empty.",
        response.getBody().getMessages().get(0));
  }

  private List<Organization> buildOrganizationStub() {
    List<Organization> list = new ArrayList<>();
    list.add(createOrganizationStub());
    return list;
  }

  private Organization createOrganizationStub() {
    Organization organization = new Organization();
    organization.setName(NAME);
    organization.setImage(IMAGE);
    organization.setAddress(ADDRESS);
    organization.setPhone(PHONE);
    organization.setEmail(EMAIL);
    organization.setWelcomeText(WELCOME_TEXT);
    organization.setTimeStamp(Timestamp.from(Instant.now()));
    return organization;
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
