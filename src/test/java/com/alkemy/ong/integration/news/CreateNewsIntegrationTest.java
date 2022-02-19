package com.alkemy.ong.integration.news;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.News;
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

    when(newsRepository.save(any(News.class))).thenReturn(stubNews());

    CreateNewsRequest createNewsRequest = buildRequestPayLoad();

    HttpEntity<CreateNewsRequest> requestEntity = new HttpEntity<>(createNewsRequest, headers);

    ResponseEntity<NewsResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        requestEntity,
        NewsResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    NewsResponse newsResponse = response.getBody();
    assertNotNull(newsResponse);
    assertNotNull(createNewsRequest);
    assertNotNull(response.getBody());

    assertEquals(createNewsRequest.getName(), newsResponse.getName());
    assertEquals(createNewsRequest.getImage(), newsResponse.getImage());
    assertEquals(createNewsRequest.getText(), newsResponse.getText());

  }

  @Test
  public void shouldReturnForbiddenWhenRoleIsUser() {

    setAuthorizationHeaderBasedOn(RoleType.USER);

    CreateNewsRequest createNewsRequest = buildRequestPayLoad();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createNewsRequest);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, response.getBody().getStatus());
  }

  @Test
  public void shouldReturnForbiddenWhenAccessWithoutAuthentication() {
    CreateNewsRequest createNewsRequest = buildRequestPayLoad();
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createNewsRequest);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, response.getBody().getStatus());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() {

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    CreateNewsRequest createNewsRequest = buildRequestWithNullName();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createNewsRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(400, response.getBody().getStatus());
    assertEquals("Name cannot be null or empty.", getFirstMessageError(response));

  }

  @Test
  public void shouldReturnBadRequestWhenTextIsEmpty() {

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    CreateNewsRequest createNewsRequest = buildRequestWithNullText();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createNewsRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(400, response.getBody().getStatus());
    assertEquals("Text cannot be null or empty.", getFirstMessageError(response));

  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmpty() {

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    CreateNewsRequest createNewsRequest = buildRequestWithNullImage();

    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(createNewsRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(400, response.getBody().getStatus());
    assertEquals("Image cannot be null or empty.", getFirstMessageError(response));

  }

  private CreateNewsRequest buildRequestWithNullName() {
    return buildRequestPayLoad(null, TEXT, IMAGE);
  }

  private CreateNewsRequest buildRequestWithNullText() {
    return buildRequestPayLoad(NAME, null, IMAGE);
  }

  private CreateNewsRequest buildRequestWithNullImage() {
    return buildRequestPayLoad(NAME, TEXT, null);
  }

  private CreateNewsRequest buildRequestPayLoad() {

    return buildRequestPayLoad(NAME, TEXT, IMAGE);
  }

  private CreateNewsRequest buildRequestPayLoad(
      String name, String text, String image) {
    return new CreateNewsRequest(name, text, image);
  }

  private ResponseEntity<ErrorResponse> getErrorResponseEntity(
      CreateNewsRequest createRequest) {

    HttpEntity<CreateNewsRequest> request =
        new HttpEntity<>(createRequest, headers);

    return restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST,
        request,
        ErrorResponse.class);
  }
}
