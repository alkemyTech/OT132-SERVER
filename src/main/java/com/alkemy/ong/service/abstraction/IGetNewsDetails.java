package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.model.response.ListNewsResponse;
import org.springframework.data.domain.Pageable;

public interface IGetNewsDetails {

  NewsResponse getBy(Long id);

  ListNewsResponse findAll(Pageable pageable);

}
