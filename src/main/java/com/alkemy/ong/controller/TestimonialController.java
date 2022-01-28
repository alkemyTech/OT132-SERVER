package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.Testimonial;
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
  public ResponseEntity<Object> create(@RequestBody @Valid Testimonial testimonial){

  return null;
  }
}
