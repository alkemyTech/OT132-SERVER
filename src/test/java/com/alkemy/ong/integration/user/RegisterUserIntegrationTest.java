package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.repository.IRoleRepository;
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
public class RegisterUserIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/auth/register";

  @MockBean
  protected IRoleRepository roleRepository;

  @MockBean
  protected IOrganizationRepository organizationRepository;

  @Test
  public void shouldRegisterUser() {
    when(roleRepository.findByName(eq(RoleType.USER.getFullRoleName())))
        .thenReturn(stubRole(RoleType.USER));
    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(null);
    when(userRepository.save(any(User.class))).thenReturn(stubUser(RoleType.USER));
    when(organizationRepository.findAll()).thenReturn(getOrganizationStub());

    UserRegisterRequest userRegisterRequest = buildRequestPayload();

    HttpEntity<UserRegisterRequest> requestEntity = new HttpEntity<>(userRegisterRequest, headers);

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        UserResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    UserResponse userResponse = response.getBody();
    assertNotNull(userResponse);
    assertEquals(userRegisterRequest.getEmail(), userResponse.getEmail());
  }

  @Test
  public void shouldReturnBadRequestIfEmailAlreadyExist() {
    UserRegisterRequest userRegisterRequest = buildRequestPayload();
    when(userRepository.findByEmail(eq(EMAIL))).thenReturn(new User());

    HttpEntity<UserRegisterRequest> requestEntity = new HttpEntity<>(userRegisterRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  private UserRegisterRequest buildRequestPayload() {
    UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
    userRegisterRequest.setFirstName("Joe");
    userRegisterRequest.setLastName("Doe");
    userRegisterRequest.setEmail(EMAIL);
    userRegisterRequest.setPassword("12345");
    return userRegisterRequest;
  }

  private List<Organization> getOrganizationStub() {
    List<Organization> organizations = new ArrayList<>();
    Organization organization = new Organization();
    organization.setAboutUsText("aboutUsText");
    organization.setName("name");
    organization.setImage("image");
    organization.setEmail("email@email.com");
    organization.setWelcomeText("welcomeText");
    organization.setAddress("address");
    organization.setPhone(1555111);
    organizations.add(organization);
    return organizations;
  }

}
