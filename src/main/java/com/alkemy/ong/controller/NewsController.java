/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.service.abstraction.IPostNews;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewsController {
	
	@Autowired
	private IPostNews newsService;

  @PostMapping
  public ResponseEntity<NewsResponse> postNews(@RequestBody @Valid NewsRequest newsRequest) {
	
	NewsResponse newsResponse = newsService.postNews();
	
	return ResponseEntity.ok(newsResponse)
	}
}
