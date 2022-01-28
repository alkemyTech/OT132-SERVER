package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ListUsersResponse;
import com.alkemy.ong.model.response.UserResponse;
import java.util.List;
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
public class ListUsersIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/users";

  @Test
  public void shouldReturnForbiddenWhenUserDoesNotHaveRolePermission() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    ResponseEntity<Object> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        Object.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  @Test
  public void shouldReturnListOfUsers() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    User user = stubUser(RoleType.ADMIN.name());
    when(userRepository.findBySoftDeleteFalse()).thenReturn(List.of(user));

    HttpEntity<Object> entity = new HttpEntity<>(headers);
    ResponseEntity<ListUsersResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        entity,
        ListUsersResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    UserResponse userResponse = response.getBody().getUserResponses().get(0);
    assertEquals(user.getFirstName(), userResponse.getFirstName());
    assertEquals(user.getLastName(), userResponse.getLastName());
    assertEquals(user.getEmail(), userResponse.getEmail());
    assertEquals(user.getPhoto(), userResponse.getPhoto());
  }

  @Test
  public void shouldReturnEmptyListOfUsers() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(userRepository.findBySoftDeleteFalse()).thenReturn(List.of());

    HttpEntity<Object> entity = new HttpEntity<>(headers);
    ResponseEntity<ListUsersResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        entity,
        ListUsersResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().getUserResponses().isEmpty());
  }

}
