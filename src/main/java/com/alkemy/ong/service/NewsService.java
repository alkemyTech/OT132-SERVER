package com.alkemy.ong.service;

import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.IAddCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.service.abstraction.IGetNewsDetails;
import com.alkemy.ong.service.abstraction.ICreateNews;

@Service
public class NewsService implements IGetNewsDetails, IAddCategory, ICreateNews {

  @Autowired private INewsRepository newsRepository;

  @Autowired private NewsMapper newsMapper;

  @Autowired private ICategoryRepository categoryRepository;

  @Override
  public NewsResponse buildResponse(News news) {

    NewsResponse newsResponse = newsMapper.responseMapper(news);

    return new NewsResponse();
  }

  @Override
  public Category add() {

    Category category = categoryRepository.findByNameIgnoreCase("news");

    return category;
  }

  @Override
  public NewsResponse create(NewsRequest newsRequest) {

    News news = newsMapper.map(newsRequest);

    news.setCategory(add());

    return buildResponse(newsRepository.save(news));
  }
}
