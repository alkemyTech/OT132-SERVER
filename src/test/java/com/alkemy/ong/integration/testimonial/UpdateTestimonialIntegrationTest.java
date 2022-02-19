package com.alkemy.ong.integration.testimonial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.UpdateTestimonialRequest;
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
public class UpdateTestimonialIntegrationTest extends AbstractBaseTestimonialIntegrationTest {

  @Test
  public void shouldUpdateTestimonialWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(testimonialRepository.save(any(Testimonial.class))).thenReturn(createTestimonialStub());
    when(testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(TESTIMONIAL_ID))
        .thenReturn(createTestimonialStub());
    UpdateTestimonialRequest request = buildRequestPayload();
    HttpEntity<UpdateTestimonialRequest> requestEntity = new HttpEntity<>(request, headers);

    ResponseEntity<TestimonialResponse> response = restTemplate.exchange(
        createURLWithPort(PATH_ID),
        HttpMethod.PUT,
        requestEntity,
        TestimonialResponse.class);
    assertSuccessResponse(response, request);
  }

  @Test
  public void shouldUpdateTestimonialWithUserRole() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    when(testimonialRepository.save(any(Testimonial.class))).thenReturn(createTestimonialStub());
    when(testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(TESTIMONIAL_ID))
        .thenReturn(createTestimonialStub());

    UpdateTestimonialRequest request = buildRequestPayload();
    HttpEntity<UpdateTestimonialRequest> requestEntity = new HttpEntity<>(request, headers);

    ResponseEntity<TestimonialResponse> response = restTemplate.exchange(
        createURLWithPort(PATH_ID),
        HttpMethod.PUT,
        requestEntity,
        TestimonialResponse.class);
    assertSuccessResponse(response, request);
  }

  @Test
  public void shouldReturnForbiddenWithNoRole() {
    UpdateTestimonialRequest updateTestimonialRequest = buildRequestPayload();

    HttpEntity<UpdateTestimonialRequest> requestEntity = new HttpEntity<>(
        updateTestimonialRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH_ID),
        HttpMethod.PUT, requestEntity, ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(0, getAmountMessages(response));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateTestimonialRequest updateTestimonialRequest = buildRequestPayloadWithEmptyName();

    HttpEntity<UpdateTestimonialRequest> requestEntity =
        new HttpEntity<>(updateTestimonialRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH_ID),
        HttpMethod.PUT,
        requestEntity,
        ErrorResponse.class);
    assertOneEmptyOrNullFieldInRequest(response, "Name cannot be empty or null.");
  }

  @Test
  public void shouldReturnBadRequestWhenContentIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateTestimonialRequest updateTestimonialRequest = buildRequestPayloadWithEmptyContent();

    HttpEntity<UpdateTestimonialRequest> requestEntity =
        new HttpEntity<>(updateTestimonialRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH_ID),
        HttpMethod.PUT,
        requestEntity,
        ErrorResponse.class);

    assertNotNull(response);
    assertOneEmptyOrNullFieldInRequest(response, "Content cannot be empty or null.");
  }

  private UpdateTestimonialRequest buildRequestPayloadWithEmptyName() {
    return buildRequestPayload(null, CONTENT);
  }

  private UpdateTestimonialRequest buildRequestPayloadWithEmptyContent() {
    return buildRequestPayload(NAME, null);
  }

  private UpdateTestimonialRequest buildRequestPayload() {
    return buildRequestPayload(NAME, CONTENT);
  }

  private UpdateTestimonialRequest buildRequestPayload(String name, String content) {
    UpdateTestimonialRequest request = new UpdateTestimonialRequest();
    request.setName(name);
    request.setContent(content);
    return request;
  }

  private void assertSuccessResponse(ResponseEntity<TestimonialResponse> response,
      UpdateTestimonialRequest request) {
    assertEquals(HttpStatus.OK, response.getStatusCode());
    TestimonialResponse testimonialResponse = response.getBody();
    assertNotNull(testimonialResponse);
    assertEquals(request.getName(), testimonialResponse.getName());
    assertEquals(request.getContent(), testimonialResponse.getContent());
  }

}
