package com.alkemy.ong.integration.member;

import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DeleteMemberIntegrationTest extends AbstractBaseMemberIntegrationTest{

  @Test
  public void shouldSoftDeleteUserWhenAccessWithAdminRole() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(memberRepository.findByMemberIdAndSoftDeleteFalse(MEMBER_ID))
        .thenReturn(optionalMemberStub());


  }
}
