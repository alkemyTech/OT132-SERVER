package com.alkemy.ong.service;

import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.ICreateNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements ICreateNews {

  @Autowired
  private INewsRepository newsRepository;

  @Autowired
  private NewsMapper newsMapper;

  @Autowired
  private ICategoryRepository categoryRepository;

  @Override
  public NewsResponse create(CreateNewsRequest createNewsRequest) {
    News news = newsMapper.map(createNewsRequest);
    news.setSoftDelete(false);
    news.setCategory(categoryRepository.findByNameIgnoreCase("news"));
    return newsMapper.map(newsRepository.save(news));
  }

}
