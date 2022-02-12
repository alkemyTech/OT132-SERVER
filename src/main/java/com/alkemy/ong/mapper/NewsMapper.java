package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import java.util.ArrayList;
import java.util.List;
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

  public List<NewsResponse> map(List<News> newsCollection) {
    List<NewsResponse> newsResponses = new ArrayList<>(newsCollection.size());
    for (News news : newsCollection) {
      newsResponses.add(map(news));
    }
    return newsResponses;
  }
}
