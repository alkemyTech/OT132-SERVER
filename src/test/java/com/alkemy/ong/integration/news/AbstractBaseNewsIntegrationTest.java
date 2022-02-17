package com.alkemy.ong.integration.news;


import static org.junit.Assert.assertNotNull;

import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.repository.ICategoryRepository;
//import com.alkemy.ong.repository.INewsRepository;
import java.sql.Timestamp;
import java.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class AbstractBaseNewsIntegrationTest extends AbstractBaseIntegrationTest {

//  @MockBean
//  protected INewsRepository newsRepository;

  @MockBean
  protected ICategoryRepository categoryRepository;

  protected static final String PATH = "/news";
  protected static final String NEWS_CATEGORY = "news";
  protected static final String NAME = "Important News!";
  protected static final String TEXT = "We found the cure for clumsy programming. Yey!!";
  protected static final String IMAGE = "http://fake-url-image.com/this-is-not-a-pipe.jpg";


  protected Category stubCategory() {
    return new Category(1l,
        "news",
        "category of news",
        null,
        Timestamp.from(Instant.now()),
        false);
  }
}
