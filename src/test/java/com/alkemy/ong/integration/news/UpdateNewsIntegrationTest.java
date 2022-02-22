package com.alkemy.ong.integration.news;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.UpdateNewsRequest;
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
public class UpdateNewsIntegrationTest extends AbstractBaseNewsIntegrationTest {

  @Test
  public void shouldUpdateNewsWithRoleAdmin() {

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(newsRepository.save(any(News.class))).thenReturn(stubNews());

    UpdateNewsRequest updateNewsRequest = buildRequestPayLoad();

    HttpEntity<UpdateNewsRequest> requestEntity = new HttpEntity<>(
        updateNewsRequest, headers);

    ResponseEntity<NewsResponse> response = restTemplate.exchange(
        createURLWithPort(PATH_ID),
        HttpMethod.PUT,
        requestEntity,
        NewsResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(200, response.getStatusCodeValue());

    NewsResponse newsResponse = response.getBody();
    assertNotNull(newsResponse);
    assertNotNull(updateNewsRequest);
    assertNotNull(response.getBody());

    assertEquals(updateNewsRequest.getName(), newsResponse.getName());
    assertEquals(updateNewsRequest.getImage(), newsResponse.getImage());
    assertEquals(updateNewsRequest.getText(), newsResponse.getText());

  }

  @Test
  public void shouldReturnForbiddenWhenRoleIsUser() {

    setAuthorizationHeaderBasedOn(RoleType.USER);

    UpdateNewsRequest updateNewsRequest = buildRequestPayLoad();

    HttpEntity<UpdateNewsRequest> request = new HttpEntity<>(updateNewsRequest, headers);
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(HttpMethod.PUT, request, PATH_ID);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnForbiddenWhitNoAuthentication() {

    UpdateNewsRequest updateNewsRequest = buildRequestPayLoad();

    HttpEntity<UpdateNewsRequest> request = new HttpEntity<>(updateNewsRequest, headers);
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(HttpMethod.PUT, request, PATH_ID);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals(403, getStatusValue(response));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() {

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateNewsRequest updateNewsRequest = buildRequestWithNullName();

    HttpEntity<UpdateNewsRequest> request = new HttpEntity<>(updateNewsRequest, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH_ID),
        HttpMethod.PUT,
        request,
        ErrorResponse.class);

    assertOneEmptyOrNullFieldInRequest(response, "Name cannot be empty or null.");
  }

  @Test
  public void shouldReturnBadRequestWhenTextIsEmpty() {

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateNewsRequest updateNewsRequest = buildRequestWithNullText();
    HttpEntity<UpdateNewsRequest> request = new HttpEntity<>(updateNewsRequest, headers);
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(HttpMethod.PUT, request, PATH_ID);

    assertOneEmptyOrNullFieldInRequest(response,"The content cannot be empty or null.");

  }
  @Test
  public void shouldReturnBadRequestWhenImageIsEmpty() {

    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    UpdateNewsRequest updateNewsRequest = buildRequestWithNullImage();
    HttpEntity<UpdateNewsRequest> request = new HttpEntity<>(updateNewsRequest, headers);
    ResponseEntity<ErrorResponse> response = getErrorResponseEntity(HttpMethod.PUT, request, PATH_ID);

    assertOneEmptyOrNullFieldInRequest(response,"Image cannot be null or empty.");
  }

  private UpdateNewsRequest buildRequestPayLoad() {

    return buildRequestPayLoad(NAME, TEXT, IMAGE);
  }

  private UpdateNewsRequest buildRequestPayLoad(
      String name, String text, String image) {
    return new UpdateNewsRequest(name, text, image);
  }

  private UpdateNewsRequest buildRequestWithNullName() {
    return buildRequestPayLoad(null, TEXT, IMAGE);
  }

  private UpdateNewsRequest buildRequestWithNullText() {
    return buildRequestPayLoad(NAME, null, IMAGE);
  }

  private UpdateNewsRequest buildRequestWithNullImage() {
    return buildRequestPayLoad(NAME, TEXT, null);
  }
}
