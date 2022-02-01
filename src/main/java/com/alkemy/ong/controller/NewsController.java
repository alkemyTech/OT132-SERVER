package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.service.abstraction.ICreateNews;

@RestController
@RequestMapping("news")
public class NewsController {

  @Autowired 
  private ICreateNews createNews;

  @PostMapping
  public ResponseEntity<NewsResponse> postNews(@RequestBody @Valid NewsRequest newsRequest) {

    NewsResponse newsResponse = createNews.create(newsRequest);

    return ResponseEntity.ok(newsResponse);
  }
}
