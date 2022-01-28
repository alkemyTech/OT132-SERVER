package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("slides")
public class SlideController {

  @Autowired
  private IGetSlideDetails getSlideDetails;

  @GetMapping
  public ResponseEntity<ListSlideResponse> list() {
    ListSlideResponse response = getSlideDetails.list();
    return ResponseEntity.ok().body(response);
  }
}
