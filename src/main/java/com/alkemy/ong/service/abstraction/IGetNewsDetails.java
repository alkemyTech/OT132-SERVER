package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.response.NewsResponse;

public interface IGetNewsDetails {

  NewsResponse buildResponse(News news);
}