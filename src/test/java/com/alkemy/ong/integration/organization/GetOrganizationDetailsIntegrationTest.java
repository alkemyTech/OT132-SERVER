package com.alkemy.ong.integration.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Organization;
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
public class GetOrganizationDetailsIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/organization/public";
  private static final String NAME = "Somos Mas";
  private static final String IMAGE = "http://foo.png";
  private static final String ADDRESS = "Street 123";
  private static final Integer PHONE = 456;

  @MockBean
  private IOrganizationRepository organizationRepository;

  @Test
  public void shouldReturnOkWhenAccessedWithoutRole() {
    when(organizationRepository.findAll()).thenReturn(buildOrganizationStub());

    ResponseEntity<OrganizationResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        OrganizationResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    OrganizationResponse responseBody = response.getBody();
    assertNotNull(responseBody);

    assertEquals(NAME, responseBody.getName());
    assertEquals(IMAGE, responseBody.getImage());
    assertEquals(ADDRESS, responseBody.getAddress());
    assertEquals(PHONE, responseBody.getPhone());

  }

  private List<Organization> buildOrganizationStub() {
    List<Organization> organizationsStub = new ArrayList<>();
    organizationsStub.add(createOrganization());
    return organizationsStub;
  }

  private Organization createOrganization() {
    Organization organization = new Organization();
    organization.setName(NAME);
    organization.setImage(IMAGE);
    organization.setAddress(ADDRESS);
    organization.setPhone(PHONE);
    organization.setTimeStamp(Timestamp.from(Instant.now()));
    return organization;
  }

}
