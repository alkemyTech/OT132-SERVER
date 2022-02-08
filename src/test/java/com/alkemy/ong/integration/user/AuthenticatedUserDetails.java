package com.alkemy.ong.integration.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.response.UserResponse;
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
public class AuthenticatedUserDetails extends AbstractBaseIntegrationTest {

  private static final String USER = "johnny@doe.com";
  private static final String ROLE = RoleType.USER.getFullRoleName();
  private static final String PATH = "/auth/me";

  @Test
  public void shouldReturnAuthenticatedUserDetails() {

    when(userRepository.findByEmail(USER)).thenReturn(stubUser(ROLE));
    setAuthorizationHeaderBasedOn(RoleType.USER);

    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(headers); 

    ResponseEntity<UserResponse> userResponse = restTemplate.exchange(createURLWithPort(PATH), 

        HttpMethod.GET, requestEntity, UserResponse.class);

    assertEquals("John", userResponse.getBody().getFirstName());
    assertEquals("Doe", userResponse.getBody().getLastName());
    assertEquals(HttpStatus.OK, userResponse.getStatusCode());

  }

}
