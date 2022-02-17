package com.alkemy.ong.integration.member;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.request.UpdateMemberRequest;
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
public class UpdateMemberIntegrationTest extends AbstractBaseMemberIntegrationTest {

  @Test
  public void shouldReturnOkWhenUserAccessWithUserRole(){
    setAuthorizationHeaderBasedOn(RoleType.USER);

    when(memberRepository.save(any(Member.class))).thenReturn(memberStub());
    when(memberRepository.findById(MEMBER_ID)).thenReturn(null);

    UpdateMemberRequest updateRequest = buildRequestPayload();

    HttpEntity<UpdateMemberRequest> request =
        new HttpEntity<>(updateRequest,headers);

    ResponseEntity<MemberResponse> response = restTemplate
        .exchange(createURLWithPort(PATH_ID),
            HttpMethod.PUT,
            request,
            MemberResponse.class);

    assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
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

    return restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST,
        request,
        ErrorResponse.class);
  }
}
