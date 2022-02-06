package com.alkemy.ong.integration.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginIntegrationTest extends AbstractBaseIntegrationTest {

  @MockBean
  private JwtUtils jwtUtils;

  private static final String EMAIL = "johnny@doe.com";
  private static final String ROLE = RoleType.USER.getFullRoleName();
  private static final String PATH = "/auth/login";

  private String token = SecurityTestConfig.createToken("johnny@doe.com", ROLE);

  @Test
  public void shouldLogin() {

    AuthenticationRequest authRequest = new AuthenticationRequest();
    authRequest.setEmail(EMAIL);
    authRequest.setPassword("1234567");
    User user = stubUser(ROLE);
    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(user);
    when(jwtUtils.generateToken(user)).thenReturn(token);

    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authRequest, headers);
    ResponseEntity<AuthenticationResponse> response = restTemplate.exchange(createURLWithPort(PATH),

        HttpMethod.POST, requestEntity, AuthenticationResponse.class); // lo que espero que devuelva

    assertEquals(authRequest.getEmail(), response.getBody().getEmail());
    assertEquals(user.getEmail(), response.getBody().getEmail());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody().getToken());
  }

  @Test
  public void shouldReturnBadRequestIfPasswordIsNull() {
    // construyo el request de prueba
    AuthenticationRequest authRequest = new AuthenticationRequest();
    authRequest.setEmail(EMAIL);
    authRequest.setPassword("");
    // cuando uso findByEmail se devuelve un usuario
    when(userRepository.findByEmail(any())).thenReturn(new User());

    // datos a ingresar al metodo
    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class); // lo que espero que devuelva

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

  }

  @Test
  // TERMINAR Y CORREGIR EN EL CODIGO: SALE STATUS 500 CUANDO PASSWORD ES INCORRECTA
  public void shouldThrowBadCredentialsPasswordIncorrect() {

    when(authenticationManager.authenticate(any())).thenReturn(null);
    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(stubUser("USER"));

    AuthenticationRequest authLogin = new AuthenticationRequest();
    authLogin.setEmail(EMAIL);
    authLogin.setPassword("WrongPassword");

    setAuthorizationHeaderBasedOn(RoleType.USER);// ??

    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authLogin, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response);

  }

}
