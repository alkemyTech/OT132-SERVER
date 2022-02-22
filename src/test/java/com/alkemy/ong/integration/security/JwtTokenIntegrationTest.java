package com.alkemy.ong.integration.security;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.request.CreateContactRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JwtTokenIntegrationTest extends AbstractBaseIntegrationTest {

  @Test
  public void shouldReturnForbiddenWithCustomErrorWhenTokenExpired() {
    setExpiredAuthorizationHeaderBasedOn(RoleType.USER);

    HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort("/users/" + USER_ID),
        HttpMethod.DELETE,
        requestEntity,
        ErrorResponse.class);

    assertCustomForbiddenResponse(response);
  }

  @Test
  public void shouldReturnForbiddenWithCustomErrorWhenCreateContactRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    HttpEntity<CreateContactRequest> requestEntity =
        new HttpEntity<>(new CreateContactRequest(), headers);

    ResponseEntity<ErrorResponse> response = restTemplate
        .exchange(createURLWithPort("/contacts"),
            HttpMethod.POST,
            requestEntity,
            ErrorResponse.class);

    assertCustomForbiddenResponse(response);
  }

}
