package com.alkemy.ong.integration.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
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
public class UpdateIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/organization/public";
  private static final String NAME = "Somos Mas";
  private static final String IMAGE = "http://foo.png";
  private static final String ADDRESS = "Street 123";
  private static final Integer PHONE = 456;

  @MockBean
  private IOrganizationRepository organizationRepository;

  @Test
  public void shouldUpdateOrganizationWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());

    UpdateOrganizationRequest updateOrganizationRequest = buildRequestPayload();

    HttpEntity<UpdateOrganizationRequest> requestEntity = new HttpEntity<>(
        updateOrganizationRequest, headers);

    ResponseEntity<OrganizationResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        OrganizationResponse.class);

    assertEquals(HttpStatus.OK,response.getStatusCode());

    OrganizationResponse organizationResponse = response.getBody();
    assertNotNull(organizationResponse);
    assertEquals(updateOrganizationRequest.getName(),organizationResponse.getName());
    assertEquals(updateOrganizationRequest.getImage(),organizationResponse.getImage());
    assertEquals(updateOrganizationRequest.getEmail(),organizationResponse.getEmail());
    assertEquals(updateOrganizationRequest.getWelcomeText(),organizationResponse.getWelcomeText());

  }

  private List<Organization> buildOrganizationStub(){
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
    organization.setTimeStamp(Timestamp.from(Instant.now()));
    return organization;
  }

  private UpdateOrganizationRequest buildRequestPayload() {
    return buildRequestPayload("Organization name", "Organization image", "user@gmail.com");
  }

  private UpdateOrganizationRequest buildRequestPayload(String name,
      String image, String email) {
    UpdateOrganizationRequest request = new UpdateOrganizationRequest();
    request.setName(name);
    request.setImage(image);
    request.setEmail(email);
    request.setWelcomeText("Welcome");
    request.setPhone(456);
    request.setAddress("Street 123");
    request.setAboutUsText("Text");
    request.setFacebookUrl("Url-f");
    request.setLinkedinUrl("Url-l");
    request.setInstagramUrl("Url-i");
    return request;
  }
}
