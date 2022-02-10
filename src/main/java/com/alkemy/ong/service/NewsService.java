package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.ListNewsResponse;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.ICreateNews;
import com.alkemy.ong.service.abstraction.IDeleteNews;
import com.alkemy.ong.service.abstraction.IGetNewsDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements ICreateNews, IDeleteNews, IGetNewsDetails {

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

  @Override
  public void delete(Long id) {
    News news = newsRepository.findByNewsIdAndSoftDeleteFalse(id);

    if (news == null) {
      throw new NotFoundException("News not found");
    }

    news.setSoftDelete(true);
    newsRepository.save(news);
  }

  @Override
  public ListNewsResponse findAll(Pageable pageable) {
    Page<News> page = newsRepository.findBySoftDeleteFalseOrderByTimestampDesc(pageable);
    List<NewsResponse> newsResponses = newsMapper.map(page.getContent());
    return buildListResponse(newsResponses, page);
  }

  private ListNewsResponse buildListResponse(List<NewsResponse> newsResponses, Page<News> page) {
    ListNewsResponse listNewsResponse = new ListNewsResponse();
    listNewsResponse.setNewsResponse(newsResponses);
    listNewsResponse.setPage(page.getNumber());
    listNewsResponse.setTotalPages(page.getTotalPages());
    listNewsResponse.setSize(page.getSize());
    return listNewsResponse;
  }
}
