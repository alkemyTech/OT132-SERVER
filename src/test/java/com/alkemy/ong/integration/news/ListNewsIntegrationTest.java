package com.alkemy.ong.integration.news;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.response.ListNewsResponse;
import com.alkemy.ong.model.response.NewsResponse;
import java.util.List;
import java.util.Objects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListNewsIntegrationTest extends AbstractBaseNewsIntegrationTest {

  private final static int PAGE = 0;
  private final static int SIZE = 1;
  private final static String PAGINATION_PATH = PATH + "?page=" + PAGE + "&size=" + SIZE;

  @Test
  public void shouldReturnOkWhenAccessedWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    when(newsRepository.findBySoftDeleteFalseOrderByTimestampDesc(any()))
        .thenReturn(buildNewsStubPage());

    ResponseEntity<ListNewsResponse> response = restTemplate.exchange(
        createURLWithPort(PAGINATION_PATH),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        ListNewsResponse.class);

    assertNotNull(response.getBody());
    List<NewsResponse> newsResponses = response.getBody().getNewsResponse();
    assertNotNull(newsResponses);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, newsResponses.size());
    assertEquals(PAGE, response.getBody().getPage());
    assertEquals(SIZE, response.getBody().getSize());
    assertEquals(1, response.getBody().getTotalPages());
    assertTrue(Objects.requireNonNull(response.getHeaders().getFirst(HttpHeaders.LINK)).isEmpty());
  }

  @Test
  public void shouldReturnForbiddenWhenAccessedWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity();

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnForbiddenWhenNotAuthenticated() {
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity();

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  protected ResponseEntity<ErrorResponse> getErrorResponseEntity() {
    return restTemplate.exchange(createURLWithPort(PAGINATION_PATH),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        ErrorResponse.class);
  }
}
