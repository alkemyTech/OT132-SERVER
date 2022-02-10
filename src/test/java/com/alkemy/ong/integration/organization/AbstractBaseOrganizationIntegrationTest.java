package com.alkemy.ong.integration.organization;

import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Organization;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AbstractBaseOrganizationIntegrationTest extends AbstractBaseIntegrationTest {

  protected static final String PATH = "/organization/public";
  protected static final String NAME = "Somos Mas";
  protected static final String IMAGE = "http://foo.png";
  protected static final String ADDRESS = "Street 123";
  protected static final Integer PHONE = 456;
  protected static final String EMAIL = "foo@gmail.com";
  protected static final String WELCOME_TEXT = "Welcome";

  protected List<Organization> buildOrganizationStub() {
    List<Organization> organizations = new ArrayList<>();
    organizations.add(createOrganizationStub());
    return organizations;
  }

  protected Organization createOrganizationStub() {
    Organization organization = new Organization();
    organization.setName(NAME);
    organization.setImage(IMAGE);
    organization.setAddress(ADDRESS);
    organization.setPhone(PHONE);
    organization.setEmail(EMAIL);
    organization.setWelcomeText(WELCOME_TEXT);
    organization.setTimeStamp(Timestamp.from(Instant.now()));
    return organization;
  }
}
