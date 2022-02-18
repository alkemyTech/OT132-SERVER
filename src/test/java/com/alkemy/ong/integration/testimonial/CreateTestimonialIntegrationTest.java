package com.alkemy.ong.integration.testimonial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.request.CreateTestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
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
public class CreateTestimonialIntegrationTest extends AbstractBaseTestimonialIntegrationTest {

  private static final String PATH = "/testimonials";

  @Test
  public void shouldCreateTestimonialWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    CreateTestimonialRequest createTestimonialRequest = buildRequestPayload();

    HttpEntity<CreateTestimonialRequest> requestEntity =
        new HttpEntity<>(createTestimonialRequest, headers);

    ResponseEntity<TestimonialResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        TestimonialResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    TestimonialResponse testimonialResponse = response.getBody();
    assertNotNull(testimonialResponse);
    assertEquals(createTestimonialRequest.getName(), testimonialResponse.getName());
    assertEquals(createTestimonialRequest.getContent(), testimonialResponse.getContent());
  }

  @Test
  public void shouldCreateTestimonialWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateTestimonialRequest createTestimonialRequest = buildRequestPayload();

    HttpEntity<CreateTestimonialRequest> requestEntity =
        new HttpEntity<>(createTestimonialRequest, headers);

    ResponseEntity<TestimonialResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        TestimonialResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    TestimonialResponse testimonialResponse = response.getBody();
    assertNotNull(testimonialResponse);
    assertEquals(createTestimonialRequest.getName(), testimonialResponse.getName());
    assertEquals(createTestimonialRequest.getContent(), testimonialResponse.getContent());
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithoutRole() {
    CreateTestimonialRequest createTestimonialRequest = buildRequestPayload();

    HttpEntity<CreateTestimonialRequest> requestEntity =
        new HttpEntity<>(createTestimonialRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(0, getAmountMessages(response));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    CreateTestimonialRequest createTestimonialRequest = buildRequestPayloadWithEmptyName();

    HttpEntity<CreateTestimonialRequest> requestEntity =
        new HttpEntity<>(createTestimonialRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    assertEquals(400, response.getBody().getStatus());
    assertEquals(1, response.getBody().getMessages().size());
    assertEquals("Name cannot be empty or null.", response.getBody().getMessages().get(0));
  }

  private CreateTestimonialRequest buildRequestPayloadWithEmptyName() {
    return buildRequestPayload(null);
  }

  private CreateTestimonialRequest buildRequestPayload() {
    return buildRequestPayload("testimonial-name");
  }

  private CreateTestimonialRequest buildRequestPayload(String name) {
    CreateTestimonialRequest createTestimonialRequest = new CreateTestimonialRequest();
    createTestimonialRequest.setContent("testimonial-content");
    createTestimonialRequest.setName(name);
    return createTestimonialRequest;
  }

}
