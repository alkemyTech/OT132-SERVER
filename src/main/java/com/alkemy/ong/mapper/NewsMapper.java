package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.attribute.NewsAttributes;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

  public NewsResponse map(News news, NewsAttributes... newsAttributes) {
    NewsResponse newsResponse = new NewsResponse();
    for (NewsAttributes newsAttribute : newsAttributes) {
      switch (newsAttribute) {
        case NEWS_ID:
          newsResponse.setNewsId(news.getNewsId());
          break;
        case NAME:
          newsResponse.setName(news.getName());
          break;
        case TEXT:
          newsResponse.setText(news.getText());
          break;
        case IMAGE:
          newsResponse.setImage(news.getImage());
          break;
        case CATEGORY_NAME:
          newsResponse.setCategoryName(news.getCategory().getName());
          break;
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("News attribute: {0} is unsupported",
                  newsAttribute));
      }
    }
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
      newsResponses.add(map(news, NewsAttributes.NEWS_ID,
          NewsAttributes.NAME,
          NewsAttributes.IMAGE,
          NewsAttributes.TEXT,
          NewsAttributes.CATEGORY_NAME));
    }
    return newsResponses;
  }
}
