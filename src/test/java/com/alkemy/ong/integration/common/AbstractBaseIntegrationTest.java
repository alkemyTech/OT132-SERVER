package com.alkemy.ong.integration.common;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IUserRepository;
import java.sql.Timestamp;
import java.time.Instant;
import org.assertj.core.util.Lists;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;

public abstract class AbstractBaseIntegrationTest {

  protected static final long USER_ID = 1L;
  protected static final String EMAIL = "johnny@doe.com";

  protected static final long ORGANIZATION_ID = 1L;

  protected TestRestTemplate restTemplate = new TestRestTemplate();
  protected HttpHeaders headers = new HttpHeaders();

  @MockBean
  protected IUserRepository userRepository;

  @MockBean
  protected AuthenticationManager authenticationManager;

  @LocalServerPort
  private int port;

  protected String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  protected void setAuthorizationHeaderBasedOn(RoleType role) {
    String jwt = SecurityTestConfig.createToken("johnny@doe.com", role);
    headers.set("Authorization", jwt);
  }

  protected Role stubRole(RoleType name) {
    Role role = new Role();
    role.setName(name.getFullRoleName());
    return role;
  }

  protected User stubUser(RoleType role) {
    return new User(USER_ID,
        "John",
        "Doe",
        "johnny@doe.com",
        "123456789",
        "https://foo.jpg",
        Lists.list(stubRole(role)),
        Timestamp.from(Instant.now()),
        false);
  }
}
