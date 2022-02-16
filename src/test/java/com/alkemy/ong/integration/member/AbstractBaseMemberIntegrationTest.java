package com.alkemy.ong.integration.member;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.repository.IMemberRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

public class AbstractBaseMemberIntegrationTest extends AbstractBaseIntegrationTest {

  protected final static String PATH = "/members";
  protected final static String NAME = "Joe";
  protected final static String FACEBOOK_URL = "facebookUrl";
  protected final static String INSTAGRAM_URL = "instagramUrl";
  protected final static String LINKEDIN_URL = "linkedinUrl";
  protected final static String IMAGE =
      "https://cohorte-enero-835eb560.s3-us-east-2.amazonaws.com/image.txt";
  protected final static String DESCRIPTION = "This is a description";
  protected final static boolean SOFT_DELETE = false;

  @MockBean
  IMemberRepository memberRepository;

  @Before
  public void checkFindMethod(){
    when(memberRepository.findBySoftDeleteFalseOrderByTimestampDesc(any()))
        .thenReturn(buildMemberStubPage());
  }

  protected Page<Member> buildMemberStubPage(){
    List<Member> members = new ArrayList<>();
    members.add(creteMember());
    return new PageImpl<>(members);
  }

  private Member creteMember() {
    Member member = new Member();
    member.setName(NAME);
    member.setFacebookUrl(FACEBOOK_URL);
    member.setInstagramUrl(INSTAGRAM_URL);
    member.setLinkedinUrl(LINKEDIN_URL);
    member.setImage(IMAGE);
    member.setDescription(DESCRIPTION);
    member.setSoftDelete(SOFT_DELETE);
    return member;
  }

  protected CreateMemberRequest buildRequestWithEmptyName(){
    return buildRequestPayload(null, IMAGE, DESCRIPTION, FACEBOOK_URL, INSTAGRAM_URL, LINKEDIN_URL);
  }

  protected CreateMemberRequest buildRequestWithEmptyImage(){
    return buildRequestPayload(NAME, null, DESCRIPTION, FACEBOOK_URL, INSTAGRAM_URL, LINKEDIN_URL);
  }

  private CreateMemberRequest buildRequestPayload(String name, String image, String description,
      String facebookUrl, String instagramUrl, String linkedinUrl) {
    CreateMemberRequest memberRequest = new CreateMemberRequest();
    memberRequest.setName(name);
    memberRequest.setImage(image);
    memberRequest.setDescription(description);
    memberRequest.setFacebookUrl(facebookUrl);
    memberRequest.setInstagramUrl(instagramUrl);
    memberRequest.setLinkedinUrl(linkedinUrl);
    return memberRequest;
  }

  protected String getFirstMessageError(ResponseEntity<ErrorResponse> response) {
    return response.getBody().getMessages().get(0);
  }

  protected int getStatusValue(ResponseEntity<ErrorResponse> response) {
    return response.getBody().getStatus();
  }

}
