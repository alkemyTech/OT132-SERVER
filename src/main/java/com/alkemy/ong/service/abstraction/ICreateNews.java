package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;

public interface ICreateNews {

  NewsResponse create(CreateNewsRequest createNewsRequest);
}
