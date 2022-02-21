package com.alkemy.ong.integration.testimonial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.response.ListTestimonialResponse;
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
public class ListTestimonialIntegrationTest extends AbstractBaseTestimonialIntegrationTest {

  private final static int PAGE = 0;
  private final static int SIZE = 10;
  private final static String PAGINATION_PATH = PATH + "?page=" + PAGE + "&size=" + SIZE;

  @Test
  public void shouldReturnOkWhenAccessedWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    ResponseEntity<ListTestimonialResponse> response = restTemplate
        .exchange(createURLWithPort(PAGINATION_PATH),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ListTestimonialResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertSuccessResponse(response);
  }

  @Test
  public void shouldReturnOkWhenAccessedWithUserRole() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    ResponseEntity<ListTestimonialResponse> response = restTemplate
        .exchange(createURLWithPort(PAGINATION_PATH),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ListTestimonialResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertSuccessResponse(response);
  }

  @Test
  public void shouldReturnForbiddenWhenAccessedWithoutRole() {
    ResponseEntity<ErrorResponse> response = restTemplate
        .exchange(createURLWithPort(PAGINATION_PATH),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  private void assertSuccessResponse(ResponseEntity<ListTestimonialResponse> response) {
    ListTestimonialResponse listTestimonialResponse = response.getBody();

    assertNotNull(listTestimonialResponse);
    assertEquals(PAGE, listTestimonialResponse.getPage());
    assertEquals(1, listTestimonialResponse.getTotalPages());
    assertTrue(Objects.requireNonNull(response.getHeaders().getFirst(HttpHeaders.LINK)).isEmpty());

    assertNotNull(listTestimonialResponse.getTestimonialResponses());
    assertEquals(1, listTestimonialResponse.getTestimonialResponses().size());
  }

}
