package com.alkemy.ong.integration.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
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

    assertEquals(HttpStatus.CREATED,response.getStatusCode());

    MemberResponse createResponse = response.getBody();
    assertNotNull(createResponse);

    assertEquals(createRequest.getName(),createResponse.getName());
    assertEquals(createRequest.getImage(),createResponse.getImage());
    assertEquals(createRequest.getDescription(),createResponse.getDescription());
    assertEquals(createRequest.getFacebookUrl(),createResponse.getFacebookUrl());
    assertEquals(createRequest.getInstagramUrl(),createResponse.getInstagramUrl());
    assertEquals(createRequest.getLinkedinUrl(),createResponse.getLinkedinUrl());
  }

  private Member memberStub(){
    Member member = new Member();
    member.setName(NAME);
    member.setImage(IMAGE);
    member.setDescription(DESCRIPTION);
    member.setFacebookUrl(FACEBOOK_URL);
    member.setInstagramUrl(INSTAGRAM_URL);
    member.setLinkedinUrl(LINKEDIN_URL);
    member.setSoftDelete(SOFT_DELETE);
    return member;
  }
}
