package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import java.sql.Timestamp;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

  public NewsResponse responseMapper(News news) {

    NewsResponse newsResponse = new NewsResponse();
    newsResponse.setImage(news.getImage());
    newsResponse.setName(news.getName());
    newsResponse.setText(news.getText());
    newsResponse.setTimestamp(news.getTimestamp());

    return newsResponse;
  }

  public News map(NewsRequest newsRequest) {

    News news = new News();
    news.setName(newsRequest.getName());
    news.setImage(newsRequest.getImage());
    news.setText(newsRequest.getText());
    news.setTimestamp(new Timestamp(System.currentTimeMillis()));

    return news;
  }
}
