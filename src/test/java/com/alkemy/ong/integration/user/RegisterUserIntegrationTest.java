package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.IRoleRepository;
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
public class RegisterUserIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/auth/register";
  private static final String EMAIL = "johnny@doe.com";

  @MockBean
  protected IRoleRepository roleRepository;

  @Test
  public void shouldRegisterUser() {
    when(roleRepository.findByName(eq(RoleType.USER.getFullRoleName())))
        .thenReturn(stubRole(RoleType.USER.name()));
    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(null);
    when(userRepository.save(any(User.class))).thenReturn(stubUser(RoleType.USER.name()));

    UserRegisterRequest userRegisterRequest = buildRequestPayload();

    HttpEntity<UserRegisterRequest> requestEntity = new HttpEntity<>(userRegisterRequest, headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, UserResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    UserResponse userResponse = response.getBody();
    assertNotNull(userResponse);
    assertEquals(userRegisterRequest.getEmail(), userResponse.getEmail());
  }

  @Test
  public void shouldReturnConflictIfEmailAlreadyExist() {
    // crea un user para reistrar
    UserRegisterRequest userRegisterRequest = buildRequestPayload();
    // CUANDO INVOCO A FINDBYEMAIL - ENTONCES ES LO QUE DEVUELVE ESE METODO. {User
    // findByEmail(String)}
    when(userRepository.findByEmail(eq("johnny@doe.com"))).thenReturn(new User());

    // entity de entrada: DATOS QUE ESTOY CREANDO PARA INGRESAR Y PROBAR METODO REGISTER
    HttpEntity<UserRegisterRequest> requestEntity = new HttpEntity<>(userRegisterRequest, headers);
    // response: LO QUE ESPERO QUE ME DEVUELVA
    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    assertNotNull(response);


  }

  private UserRegisterRequest buildRequestPayload() {
    UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
    userRegisterRequest.setFirstName("Joe");
    userRegisterRequest.setLastName("Doe");
    userRegisterRequest.setEmail(EMAIL);
    userRegisterRequest.setPassword("12345");
    return userRegisterRequest;
  }

}
