package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;

public interface ICreateNews {

  NewsResponse create(NewsRequest newsRequest);
}
