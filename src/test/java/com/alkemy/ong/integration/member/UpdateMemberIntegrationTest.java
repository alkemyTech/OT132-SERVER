package com.alkemy.ong.integration.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.request.UpdateMemberRequest;
import com.alkemy.ong.model.response.MemberResponse;
import java.util.Optional;
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
public class UpdateMemberIntegrationTest extends AbstractBaseMemberIntegrationTest {

  @Test
  public void shouldReturnOkWhenUserAccessWithUserRole() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    when(memberRepository.save(any(Member.class))).thenReturn(memberStub());
    when(memberRepository.findByMemberIdAndSoftDeleteFalse(MEMBER_ID))
        .thenReturn(optionalMemberStub());

    UpdateMemberRequest updateRequest = buildRequestPayload();

    HttpEntity<UpdateMemberRequest> request =
        new HttpEntity<>(updateRequest, headers);

    ResponseEntity<MemberResponse> response = restTemplate
        .exchange(createURLWithPort(PATH_ID),
            HttpMethod.PUT,
            request,
            MemberResponse.class);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateMemberRequest updateRequest = buildRequestPayload();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(updateRequest);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertNotNull(response.getBody());
  }

  @Test
  public void shouldReturnNotFoundWhenMemberDoesNotExist() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    when(memberRepository.findByMemberIdAndSoftDeleteFalse(MEMBER_ID))
        .thenReturn(Optional.empty());

    UpdateMemberRequest updateRequest = buildRequestPayload();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(updateRequest);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(1, getAmountMessages(response));
    assertEquals("Member not found", getFirstMessageError(response));
    assertEquals(404, getStatusValue(response));
  }

  @Test
  public void shouldReturnBadRequestWithEmptyName() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateMemberRequest updateRequest = buildRequestWithEmptyName();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(updateRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, getAmountMessages(response));
    assertEquals("Name cannot be null or empty.", getFirstMessageError(response));
    assertEquals(400, getStatusValue(response));
  }

  @Test
  public void shouldReturnBadRequestWithEmptyImage() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateMemberRequest updateRequest = buildRequestWithEmptyImage();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(updateRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, getAmountMessages(response));
    assertEquals("Image cannot be null or empty.", getFirstMessageError(response));
    assertEquals(400, getStatusValue(response));
  }

  private UpdateMemberRequest buildRequestWithEmptyName() {
    return buildRequestPayload(null, IMAGE);
  }

  private UpdateMemberRequest buildRequestWithEmptyImage() {
    return buildRequestPayload(NAME, null);
  }

  private UpdateMemberRequest buildRequestPayload() {
    return buildRequestPayload(NAME, IMAGE);
  }

  private UpdateMemberRequest buildRequestPayload(String name, String image) {
    UpdateMemberRequest memberRequest = new UpdateMemberRequest();
    memberRequest.setName(name);
    memberRequest.setImage(image);
    memberRequest.setDescription(DESCRIPTION);
    memberRequest.setFacebookUrl(FACEBOOK_URL);
    memberRequest.setInstagramUrl(INSTAGRAM_URL);
    memberRequest.setLinkedinUrl(LINKEDIN_URL);
    return memberRequest;
  }

  private ResponseEntity<ErrorResponse> getErrorResponseEntity(
      UpdateMemberRequest updateRequest) {

    HttpEntity<UpdateMemberRequest> request =
        new HttpEntity<>(updateRequest, headers);

    return restTemplate.exchange(createURLWithPort(PATH_ID),
        HttpMethod.PUT,
        request,
        ErrorResponse.class);
  }
}
