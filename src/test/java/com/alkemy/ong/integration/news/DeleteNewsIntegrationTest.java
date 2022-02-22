package com.alkemy.ong.integration.news;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
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
public class DeleteNewsIntegrationTest extends AbstractBaseNewsIntegrationTest {

  @Test
  public void shouldSetSoftDeleteTrueWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    checkFindMethod();

    HttpEntity<Object> request = new HttpEntity<>(headers);
    ResponseEntity<Void> response = restTemplate.exchange(
        createURLWithPort(PATH_ID),
        HttpMethod.DELETE,
        request,
        Void.class);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void shouldReturnForbiddenWhenRoleIsUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    HttpEntity<Object> request = new HttpEntity<>(headers);
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(HttpMethod.DELETE, request,
        PATH_ID);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnForbiddenWhenNotAuthenticated() {

    HttpEntity<Object> request = new HttpEntity<>(headers);
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(HttpMethod.DELETE, request,
        PATH_ID);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnNotFoundWhenNewsDoesNotExist() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(newsRepository
        .findByNewsIdAndSoftDeleteFalse(NEWS_ID))
        .thenReturn(null);

    HttpEntity<Object> request = new HttpEntity<>(headers);
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(
        HttpMethod.DELETE,
        request,
        PATH_ID);

    assertObjectNotFound(response, "News not found");
  }
}
