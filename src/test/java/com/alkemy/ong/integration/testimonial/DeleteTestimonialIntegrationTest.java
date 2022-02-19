package com.alkemy.ong.integration.testimonial;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
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
public class DeleteTestimonialIntegrationTest extends AbstractBaseTestimonialIntegrationTest {

  @Test
  public void shouldSoftDeleteUserWhenAccessWithAdminRole() {
    when(testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(eq(TESTIMONIAL_ID))).thenReturn(
        createTestimonialStub());
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    HttpEntity<Object> request = new HttpEntity<>(headers);

    ResponseEntity<Void> response = restTemplate
        .exchange(createURLWithPort(PATH_ID),
            HttpMethod.DELETE,
            request,
            Void.class);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void shouldSoftDeleteUserWhenAccessWithUserRole() {
    when(testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(eq(TESTIMONIAL_ID))).thenReturn(
        createTestimonialStub());
    setAuthorizationHeaderBasedOn(RoleType.USER);
    HttpEntity<Object> request = new HttpEntity<>(headers);

    ResponseEntity<Void> response = restTemplate
        .exchange(createURLWithPort(PATH_ID),
            HttpMethod.DELETE,
            request,
            Void.class);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithoutRole() {

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity();

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  @Test
  public void shouldReturnNotFoundWhenTestimonialDoesNotExist() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(eq(TESTIMONIAL_ID))).thenReturn(
        null);
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity();

    assertObjectNotFound(response, "Testimonial not found.");
  }

  private ResponseEntity<ErrorResponse> getErrorResponseEntity() {
    return restTemplate.exchange(createURLWithPort(PATH_ID),
        HttpMethod.DELETE,
        new HttpEntity<>(headers),
        ErrorResponse.class);
  }
}
