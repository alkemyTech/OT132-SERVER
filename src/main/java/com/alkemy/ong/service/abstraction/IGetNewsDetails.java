package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.NewsResponse;

public interface IGetNewsDetails {

  NewsResponse getBy(Long id);
}
