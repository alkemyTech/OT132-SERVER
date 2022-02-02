package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.model.request.CreateSlideRequest;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.service.abstraction.ICreateSlide;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slides")
public class SlideController {

  @Autowired
  private IGetSlideDetails getSlideDetails;

  @Autowired
  private ICreateSlide createSlide;

  @GetMapping
  public ResponseEntity<ListSlideResponse> list() {
    return ResponseEntity.ok().body(getSlideDetails.list());
  }

  @PostMapping
  public ResponseEntity<SlideResponse> create(
      @RequestBody @Valid CreateSlideRequest request,
      @RequestHeader("x-filename") String name,
      @RequestHeader("x-contenttype") String contentType) throws ExternalServiceException {
    return ResponseEntity
           .status(HttpStatus.CREATED)
           .body(createSlide.create(request, name, contentType));
  }
}
