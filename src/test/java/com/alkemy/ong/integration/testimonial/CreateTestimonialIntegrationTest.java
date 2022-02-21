package com.alkemy.ong.integration.testimonial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Testimonial;
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

  @Test
  public void shouldCreateTestimonialWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(testimonialRepository.save(any(Testimonial.class))).thenReturn(createTestimonialStub());

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
    when(testimonialRepository.save(any(Testimonial.class))).thenReturn(createTestimonialStub());
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

    assertOneEmptyOrNullFieldInRequest(response, "Name cannot be empty or null.");
  }

  private CreateTestimonialRequest buildRequestPayloadWithEmptyName() {
    return buildRequestPayload(null);
  }

  private CreateTestimonialRequest buildRequestPayload() {
    return buildRequestPayload(NAME);
  }

  private CreateTestimonialRequest buildRequestPayload(String name) {
    CreateTestimonialRequest createTestimonialRequest = new CreateTestimonialRequest();
    createTestimonialRequest.setContent(CONTENT);
    createTestimonialRequest.setName(name);
    return createTestimonialRequest;
  }

}
