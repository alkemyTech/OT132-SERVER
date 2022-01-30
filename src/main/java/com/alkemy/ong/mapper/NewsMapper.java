package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import java.sql.Timestamp;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

  public NewsResponse responseMapper(NewsRequest newsRequest) {

    NewsResponse newsResponse = new NewsResponse();
    newsResponse.setImage(newsRequest.getImage());
    newsResponse.setName(newsRequest.getName());
    newsResponse.setText(newsRequest.getText());
    newsResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));

    return newsResponse;
  }
	
	public News map(NewsResponse newsResponse){
		
		News news = new News();
    news.setName(newsResponse.getName());
		news.setImage(newsResponse.getImage());
		news.setText(newsResponse.getText());
    news.setTimestamp(newsResponse.getTimestamp());
		
		//Wait for CategoryService to merge and create method to get news Category. findByName in repository
		
		return news;
	}
}
