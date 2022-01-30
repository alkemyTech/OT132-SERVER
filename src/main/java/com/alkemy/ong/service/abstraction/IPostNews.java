package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;

public interface IPostNews {

  NewsResponse postNews(NewsRequest newsRequest);
}
