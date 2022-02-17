package com.alkemy.ong.integration.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ListMembersIntegrationTest extends AbstractBaseMemberIntegrationTest {

<<<<<<< HEAD
  private final static int PAGE= 0;
  private final static int SIZE = 10;
  private final static String PAGINATION_PATH = PATH+"?page="+PAGE+"&size="+SIZE;

  @Test
  public void shouldReturnOkWhenAccessedWithAdminRole(){
=======
  private final static int PAGE = 0;
  private final static int SIZE = 10;
  private final static String PAGINATION_PATH = PATH + "?page=" + PAGE + "&size=" + SIZE;

  @Test
  public void shouldReturnOkWhenAccessedWithAdminRole() {
>>>>>>> main
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    ResponseEntity<ListMembersResponse> response = restTemplate
        .exchange(createURLWithPort(PAGINATION_PATH),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ListMembersResponse.class);

<<<<<<< HEAD
    assertEquals(HttpStatus.OK,response.getStatusCode());
    List<MemberResponse> membersResponse = response.getBody().getMemberResponses();
    assertNotNull(membersResponse);
    assertEquals(1,membersResponse.size());
    assertEquals(PAGE,response.getBody().getPage());
    assertEquals(1,response.getBody().getTotalPages());
=======
    assertEquals(HttpStatus.OK, response.getStatusCode());
    List<MemberResponse> membersResponse = response.getBody().getMemberResponses();
    assertNotNull(membersResponse);
    assertEquals(1, membersResponse.size());
    assertEquals(PAGE, response.getBody().getPage());
    assertEquals(1, response.getBody().getTotalPages());
>>>>>>> main
    assertTrue(response.getHeaders().getFirst(HttpHeaders.LINK).isEmpty());
  }

  @Test
<<<<<<< HEAD
  public void shouldReturnForbiddenWhenAccessedWithUserRole(){
=======
  public void shouldReturnForbiddenWhenAccessedWithUserRole() {
>>>>>>> main
    setAuthorizationHeaderBasedOn(RoleType.USER);

    ResponseEntity<ErrorResponse> response = restTemplate
        .exchange(createURLWithPort(PAGINATION_PATH),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ErrorResponse.class);

<<<<<<< HEAD
    assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
=======
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
>>>>>>> main
  }
}
