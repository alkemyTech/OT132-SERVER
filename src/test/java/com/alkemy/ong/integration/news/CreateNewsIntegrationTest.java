package com.alkemy.ong.integration.news;

import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewsIntegrationTest {

  @Mock
  private INewsRepository newsRepository;

  @InjectMocks
  private NewsService newsService;


  @Test
  public NewsResponse shouldCreateNewsAndAResponse(){

    return null;
  }
}
