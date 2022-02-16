package com.alkemy.ong.integration.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.response.ListMembersResponse;
import com.alkemy.ong.model.response.MemberResponse;
import java.util.List;
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
public class GetMemberDetailsIntegrationTest extends AbstractBaseMemberIntegrationTest {

  private final static int PAGE= 0;
  private final static int SIZE = 10;

  @Test
  public void shouldReturnOkWhenAccessedWithAdminRole(){
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    String url = PATH+"?page="+PAGE+"&size="+SIZE;

    ResponseEntity<ListMembersResponse> response = restTemplate
        .exchange(createURLWithPort(url),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ListMembersResponse.class);

    assertEquals(HttpStatus.OK,response.getStatusCode());
    List<MemberResponse> membersResponse = response.getBody().getMemberResponses();
    assertNotNull(membersResponse);
    assertEquals(1,membersResponse.size());
    assertEquals(PAGE,response.getBody().getPage());
    assertEquals(1,response.getBody().getTotalPages());
  }

  @Test
  public void shouldReturnForbiddenWhenAccessedWithUserRole(){
    setAuthorizationHeaderBasedOn(RoleType.USER);

    String url = PATH+"?page="+PAGE+"&size="+SIZE;

    ResponseEntity<ErrorResponse> response = restTemplate
        .exchange(createURLWithPort(url),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
  }
}
