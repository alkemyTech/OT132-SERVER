package com.alkemy.ong.integration.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class GetOrganizationDetailsIntegrationTest extends AbstractBaseOrganizationIntegrationTest {

  @Test
  public void shouldReturnOkWhenAccessedWithoutRole() {
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

}
