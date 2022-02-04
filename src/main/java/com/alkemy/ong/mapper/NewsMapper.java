package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

  public NewsResponse map(News news) {
    NewsResponse newsResponse = new NewsResponse();
    newsResponse.setImage(news.getImage());
    newsResponse.setName(news.getName());
    newsResponse.setText(news.getText());
    return newsResponse;
  }

  public News map(CreateNewsRequest createNewsRequest) {
    News news = new News();
    news.setName(createNewsRequest.getName());
    news.setImage(createNewsRequest.getImage());
    news.setText(createNewsRequest.getText());
    return news;
  }

}
