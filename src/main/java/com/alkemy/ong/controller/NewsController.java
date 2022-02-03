package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.service.abstraction.ICreateNews;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewsController {

  @Autowired
  private ICreateNews createNews;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<NewsResponse> create(
      @RequestBody @Valid CreateNewsRequest createNewsRequest) {

    return ResponseEntity.status(HttpStatus.CREATED).body(createNews.create(createNewsRequest));
  }
}
