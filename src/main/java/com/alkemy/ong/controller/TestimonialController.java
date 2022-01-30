package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.TestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import com.alkemy.ong.service.abstraction.IPostTestimonial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testimonials")
public class TestimonialController {

  @Autowired
  private IPostTestimonial postTestimonial;

  @PostMapping
  public ResponseEntity<TestimonialResponse> create(
      @RequestBody TestimonialRequest testimonialRequest) {
    TestimonialResponse testimonialResponse = postTestimonial.create(testimonialRequest);
    return ResponseEntity.ok().body(testimonialResponse);
  }
}
