package com.alkemy.ong.integration.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
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
public class DeleteMemberIntegrationTest extends AbstractBaseMemberIntegrationTest {

  @Test
  public void shouldSoftDeleteUserWhenAccessWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(memberRepository.findByMemberIdAndSoftDeleteFalse(MEMBER_ID))
        .thenReturn(optionalMemberStub());

    HttpEntity<Object> request = new HttpEntity<>(headers);

    ResponseEntity<Void> response = restTemplate
        .exchange(createURLWithPort(PATH_ID),
            HttpMethod.DELETE,
            request,
            Void.class);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void shouldReturnNotFoundWhenMemberDoesNotExist() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(memberRepository.findByMemberIdAndSoftDeleteFalse(MEMBER_ID))
        .thenReturn(Optional.empty());

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity();

    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(1,getAmountMessages(response));
    assertEquals("Member not found",getFirstMessageError(response));
    assertEquals(404,getStatusValue(response));
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithUserRole() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity();

    assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
  }

  private ResponseEntity<ErrorResponse> getErrorResponseEntity() {
    return restTemplate.exchange(createURLWithPort(PATH_ID),
        HttpMethod.DELETE,
        new HttpEntity<>(headers),
        ErrorResponse.class);
  }

}
