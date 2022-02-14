package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UpdateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;

public interface IUpdateNews {

  NewsResponse update(Long id, UpdateNewsRequest updateNewsRequest);

}
