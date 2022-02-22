package com.alkemy.ong.integration.news;

import static org.mockito.Mockito.when;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.repository.INewsRepository;
import java.sql.Timestamp;
import java.time.Instant;
import org.junit.Before;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public abstract class AbstractBaseNewsIntegrationTest extends AbstractBaseIntegrationTest {

  protected static final Long NEWS_ID = 1L;
  protected static final String PATH = "/news";
  protected static final String PATH_ID = PATH + "/" + NEWS_ID;
  protected static final String NEWS_CATEGORY = "news";
  protected static final String NAME = "Important News!";
  protected static final String TEXT = "We found the cure for clumsy programming. Yey!!";
  protected static final String IMAGE = "http://fake-url-image.com/this-is-not-a-pipe.jpg";

  @MockBean
  protected INewsRepository newsRepository;

  @MockBean
  protected ICategoryRepository categoryRepository;

  @Before
  public void checkFindMethod() {
    when(newsRepository.findByNewsIdAndSoftDeleteFalse(NEWS_ID))
        .thenReturn(stubNews());
  }

  protected Category stubCategory() {
    return new Category(1L,
        "news",
        "category of news",
        null,
        Timestamp.from(Instant.now()),
        false);
  }

  protected News stubNews() {
    return new News(NEWS_ID, NAME, TEXT, IMAGE, stubCategory(), false,
        Timestamp.from(Instant.now()));
  }

  protected ResponseEntity<ErrorResponse> getErrorResponseEntity(
      CreateNewsRequest createRequest) {

    HttpEntity<CreateNewsRequest> request =
        new HttpEntity<>(createRequest, headers);

    return restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST,
        request,
        ErrorResponse.class);
  }

  protected ResponseEntity<ErrorResponse> getErrorResponseEntity(HttpMethod httpMethod, HttpEntity request) {

    return restTemplate.exchange(createURLWithPort(PATH_ID),
        httpMethod,
        request,
        ErrorResponse.class);
  }
}
