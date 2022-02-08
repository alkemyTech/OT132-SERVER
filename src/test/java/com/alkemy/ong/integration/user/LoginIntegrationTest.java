package com.alkemy.ong.integration.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.integration.common.SecurityTestConfig;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.response.AuthenticationResponse;
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
public class LoginIntegrationTest extends AbstractBaseIntegrationTest {

  private static final RoleType ROLE = RoleType.USER;
  private static final String PATH = "/auth/login";
  private static final String TOKEN = SecurityTestConfig.createToken("johnny@doe.com", ROLE);

  @MockBean
  private JwtUtils jwtUtils;

  @Test
  public void shouldThrowUnauthorizedWhenUserDoesNotExist() {
    AuthenticationRequest authRequest = buildAuthenticationRequest("123456789");
    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(null);

    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
  }

  @Test
  public void shouldLogin() {
    AuthenticationRequest authRequest = buildAuthenticationRequest("1234567");
    User user = stubUser(ROLE);

    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(user);
    when(jwtUtils.generateToken(user)).thenReturn(TOKEN);

    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authRequest, headers);
    ResponseEntity<AuthenticationResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        AuthenticationResponse.class);

    assertEquals(authRequest.getEmail(), response.getBody().getEmail());
    assertEquals(user.getEmail(), response.getBody().getEmail());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody().getToken());
    assertEquals(TOKEN, response.getBody().getToken());
  }

  @Test
  public void shouldReturnBadRequestIfEmailIsNull() {
    AuthenticationRequest authRequest = new AuthenticationRequest();
    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(null);

    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  private AuthenticationRequest buildAuthenticationRequest(String password) {
    AuthenticationRequest authRequest = new AuthenticationRequest();
    authRequest.setEmail(EMAIL);
    authRequest.setPassword(password);
    return authRequest;
  }

}
