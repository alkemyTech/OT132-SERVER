package com.alkemy.ong.integration.news;

import static org.mockito.Mockito.when;

import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.repository.INewsRepository;
import org.junit.Before;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class AbstractBaseNewsIntegrationTest extends AbstractBaseIntegrationTest {

  @MockBean
  protected INewsRepository newsRepository;

  @MockBean
  protected ICategoryRepository categoryRepository;

  protected static final String CATEGORY = "news";
  protected static final String NAME = "Important News!";
  protected static final String TEXT = "We found the cure for bad programming. Yes, you heard me.";
  protected static final String IMAGE= "http://fake-url-image.com/this-is-not-a-pipe.jpg";

  protected Category stubCategory(String categoryName){
    return categoryRepository.findByNameIgnoreCase(categoryName);
  }
}
