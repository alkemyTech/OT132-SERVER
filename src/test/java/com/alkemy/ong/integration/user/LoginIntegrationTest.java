package com.alkemy.ong.integration.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.AuthenticationRequest;  
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String EMAIL = "johnny@doe.com";
  private static final String EMPTY_PASSWORD = "";
  private static final String WRONG_PASSWORD = "12345678";

  
  private static final String PATH = "/auth/login";

  @Test
  public void shouldReturnBadRequestIfPasswordIsNull(){

    AuthenticationRequest authRequest = buildRequestPayload();//construyo el request de prueba

    when(userRepository.findByEmail(any())).thenReturn(new User());// cuando uso findByEmail se devuelve un usuario

    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authRequest, headers); //datos a ingresar al metodo

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class); //lo que espero que devuelva
    
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

  }

  @Test
  public void shouldThrowBadCredentialsPasswordIncorrect(){
    
    when(authenticationManager.authenticate(any())).thenReturn(null);
    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(stubUser("USER"));

    AuthenticationRequest authLogin = new AuthenticationRequest();
    authLogin.setEmail(EMAIL);
    authLogin.setPassword(WRONG_PASSWORD);

    setAuthorizationHeaderBasedOn(RoleType.USER);//??

    HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(authLogin, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

      assertEquals(HttpStatus.FORBIDDEN, response);



  }

  private AuthenticationRequest buildRequestPayload(){
    AuthenticationRequest authLogin = new AuthenticationRequest();
    authLogin.setEmail(EMAIL);
    authLogin.setPassword(EMPTY_PASSWORD);
    return authLogin;
  }
  
}
