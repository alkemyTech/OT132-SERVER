package com.alkemy.ong.integration.news;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateNewsIntegrationTest extends AbstractBaseNewsIntegrationTest {


  @Test
  public NewsResponse shouldCreateNewsWithRoleAdmin() {
    setAuthorizationHeaderBasedOn(RoleType.ADMIN);

    when(categoryRepository.findByNameIgnoreCase(eq("NeWs")))
        .thenReturn(stubCategory(CATEGORY));

    return null;
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
