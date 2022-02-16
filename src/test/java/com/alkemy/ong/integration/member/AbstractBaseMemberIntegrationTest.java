package com.alkemy.ong.integration.member;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.repository.IMemberRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
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

  protected CreateMemberRequest buildRequestWithEmptyName(){
    return buildRequestPayload(null, IMAGE, DESCRIPTION, FACEBOOK_URL, INSTAGRAM_URL, LINKEDIN_URL);
  }

  protected CreateMemberRequest buildRequestWithEmptyImage(){
    return buildRequestPayload(NAME, null, DESCRIPTION, FACEBOOK_URL, INSTAGRAM_URL, LINKEDIN_URL);
  }

  protected CreateMemberRequest buildRequestWithEmptyDescription(){
    return buildRequestPayload(NAME, IMAGE, null, FACEBOOK_URL, INSTAGRAM_URL, LINKEDIN_URL);
  }

  protected CreateMemberRequest buildRequestWithEmptyFacebookUrl(){
    return buildRequestPayload(NAME, IMAGE, DESCRIPTION, null, INSTAGRAM_URL, LINKEDIN_URL);
  }

  protected CreateMemberRequest buildRequestWithEmptyInstagramUrl(){
    return buildRequestPayload(NAME, IMAGE, DESCRIPTION, FACEBOOK_URL, null, LINKEDIN_URL);
  }

  protected CreateMemberRequest buildRequestWithEmptyLinkedinUrl(){
    return buildRequestPayload(NAME, IMAGE, DESCRIPTION, FACEBOOK_URL, INSTAGRAM_URL, null);
  }

  protected CreateMemberRequest buildRequestPayload() {
    return buildRequestPayload(NAME, IMAGE, DESCRIPTION, FACEBOOK_URL, INSTAGRAM_URL, LINKEDIN_URL);
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
