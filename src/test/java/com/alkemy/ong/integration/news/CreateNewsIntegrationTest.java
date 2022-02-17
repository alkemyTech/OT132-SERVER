package com.alkemy.ong.integration.news;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateNewsIntegrationTest extends AbstractBaseNewsIntegrationTest {


  @Test
  public void shouldCreateNewsWithRoleAdmin() {

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(categoryRepository.findByNameIgnoreCase(eq(NEWS_CATEGORY)))
        .thenReturn(stubCategory());

    CreateNewsRequest createNewsRequest = buildRequestPayLoad();

    HttpEntity<CreateNewsRequest> requestEntity = new HttpEntity<>(createNewsRequest, headers);

    ResponseEntity<NewsResponse> response = restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, requestEntity, NewsResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

  }

  private CreateNewsRequest buildRequestPayLoad() {
    return buildRequestPayLoad(NAME, TEXT, IMAGE);
  }

  private CreateNewsRequest buildRequestPayLoad(String name, String text, String image) {
    CreateNewsRequest createNewsRequest = new CreateNewsRequest();
    createNewsRequest.setName(name);
    createNewsRequest.setText(text);
    createNewsRequest.setImage(image);
    return createNewsRequest;
  }
}
