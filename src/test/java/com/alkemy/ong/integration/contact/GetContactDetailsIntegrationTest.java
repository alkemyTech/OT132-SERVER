package com.alkemy.ong.integration.contact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.response.ListContactResponse;
import com.alkemy.ong.repository.IContactRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetContactDetailsIntegrationTest extends AbstractBaseContactIntegrationTest {

  @MockBean
  private IContactRepository contactRepository;

  @Test
  public void shouldReturnOkWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);
    when(contactRepository.findAll()).thenReturn(buildContactStub());

    ResponseEntity<ListContactResponse> response = restTemplate
        .exchange(createURLWithPort(PATH),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ListContactResponse.class);

    assertEquals(HttpStatus.OK,response.getStatusCode());
    ListContactResponse listContactResponse = response.getBody();
    assertNotNull(listContactResponse);
  }

  @Test
  public void shouldReturnForbiddenWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);

    ResponseEntity<ErrorResponse> response = restTemplate
        .exchange(createURLWithPort(PATH),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            ErrorResponse.class);

    assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode());
    ErrorResponse errorResponse = response.getBody();
    assertNotNull(errorResponse);
    assertEquals(403, errorResponse.getStatus());
  }

  private List<Contact> buildContactStub() {
    List<Contact> contactStub = new ArrayList<>();
    contactStub.add(createContact());
    return contactStub;
  }

  private Contact createContact() {
    Contact contact = new Contact();
    contact.setContactId(1L);
    contact.setName(NAME);
    contact.setEmail(EMAIL);
    contact.setPhone(PHONE);
    contact.setMessage(MESSAGE);
    contact.setDeletedAt(null);
    return contact;
  }
}
