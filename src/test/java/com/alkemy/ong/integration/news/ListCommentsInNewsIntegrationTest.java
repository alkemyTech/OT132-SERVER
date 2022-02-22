package com.alkemy.ong.integration.news;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.TimestampUtils;
import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ListCommentsInNewsResponse;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.repository.ICommentRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListCommentsInNewsIntegrationTest extends AbstractBaseNewsIntegrationTest {

  private static final String COMMENT_BODY = "body";

  @MockBean
  protected ICommentRepository commentRepository;

  @Test
  public void shouldReturnOkWhenAccessedWithRoleUser() {
    setAuthorizationHeaderBasedOn(RoleType.USER);
    when(newsRepository.findByNewsIdAndSoftDeleteFalse(eq(NEWS_ID))).thenReturn(stubNews());
    when(commentRepository.findByNewsId(eq(NEWS_ID))).thenReturn(stubComments());

    ResponseEntity<ListCommentsInNewsResponse> response = restTemplate.exchange(
        createURLWithPort(PATH_ID + "/comments"),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        ListCommentsInNewsResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    ListCommentsInNewsResponse responseBody = response.getBody();
    assertNotNull(responseBody);
    NewsResponse newsResponse = responseBody.getNews();
    assertEquals(NAME, newsResponse.getName());

    assertEquals(1, newsResponse.getComments().size());
    assertEquals(COMMENT_BODY, newsResponse.getComments().get(0).getBody());
    assertEquals(EMAIL, newsResponse.getComments().get(0).getUsername());
  }

  private List<Comment> stubComments() {
    User user = new User();
    user.setEmail(EMAIL);

    Comment comment = new Comment();
    comment.setBody(COMMENT_BODY);
    comment.setTimestamp(TimestampUtils.now());
    comment.setUser(user);
    return List.of(comment);
  }
}
