package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.TestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testimonials")
public class TestimonialController {

  @PostMapping
  public ResponseEntity<TestimonialResponse> create(@RequestBody @Valid TestimonialRequest testimonialRequest) {

    return null;
  }
}
