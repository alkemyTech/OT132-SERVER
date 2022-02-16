package com.alkemy.ong.integration.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.model.response.MemberResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateMemberIntegrationTest extends AbstractBaseMemberIntegrationTest {

  @Test
  public void shouldReturnOkWhenAccessWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    when(memberRepository.save(any(Member.class))).thenReturn(memberStub());

    CreateMemberRequest createRequest = buildRequestPayload();

    HttpEntity<CreateMemberRequest> request =
        new HttpEntity<>(createRequest, headers);

    ResponseEntity<MemberResponse> response = restTemplate
        .exchange(createURLWithPort(PATH),
            HttpMethod.POST,
            request,
            MemberResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    MemberResponse createResponse = response.getBody();
    assertNotNull(createResponse);

    assertEquals(createRequest.getName(), createResponse.getName());
    assertEquals(createRequest.getImage(), createResponse.getImage());
    assertEquals(createRequest.getDescription(), createResponse.getDescription());
    assertEquals(createRequest.getFacebookUrl(), createResponse.getFacebookUrl());
    assertEquals(createRequest.getInstagramUrl(), createResponse.getInstagramUrl());
    assertEquals(createRequest.getLinkedinUrl(), createResponse.getLinkedinUrl());
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    CreateMemberRequest createRequest = buildRequestPayload();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createRequest);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithoutRole() {
    CreateMemberRequest createRequest = buildRequestPayload();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createRequest);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateMemberRequest createRequest = buildRequestWithEmptyName();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createRequest);

    assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    ErrorResponse error = response.getBody();
    assertEquals(400,getStatusValue(response));
    assertEquals("Name cannot be null or empty.",getFirstMessageError(response));
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmpty() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateMemberRequest createRequest = buildRequestWithEmptyImage();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createRequest);

    assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    assertEquals(400,getStatusValue(response));
    assertEquals("Image cannot be null or empty.",getFirstMessageError(response));
  }

  protected CreateMemberRequest buildRequestWithEmptyName(){
    return buildRequestPayload(null, IMAGE);
  }

  protected CreateMemberRequest buildRequestWithEmptyImage(){
    return buildRequestPayload(NAME, null);
  }

  protected CreateMemberRequest buildRequestPayload() {
    return buildRequestPayload(NAME, IMAGE);
  }

  private CreateMemberRequest buildRequestPayload(String name, String image) {
    CreateMemberRequest memberRequest = new CreateMemberRequest();
    memberRequest.setName(name);
    memberRequest.setImage(image);
    memberRequest.setDescription(DESCRIPTION);
    memberRequest.setFacebookUrl(FACEBOOK_URL);
    memberRequest.setInstagramUrl(INSTAGRAM_URL);
    memberRequest.setLinkedinUrl(LINKEDIN_URL);
    return memberRequest;
  }

  private ResponseEntity<ErrorResponse> getErrorResponseEntity(
      CreateMemberRequest createRequest) {

    HttpEntity<CreateMemberRequest> request =
        new HttpEntity<>(createRequest, headers);

    return restTemplate.exchange(createURLWithPort(PATH),
            HttpMethod.POST,
            request,
            ErrorResponse.class);
  }
}
