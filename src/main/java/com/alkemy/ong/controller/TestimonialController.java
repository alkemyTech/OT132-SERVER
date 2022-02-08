package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CreateTestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import com.alkemy.ong.service.abstraction.ICreateTestimonial;
import com.alkemy.ong.service.abstraction.IDeleteTestimonial;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

  @Autowired
  private ICreateTestimonial createTestimonial;

  @Autowired
  private IDeleteTestimonial deleteTestimonial;

  @PostMapping
  public ResponseEntity<TestimonialResponse> create(
      @RequestBody @Valid CreateTestimonialRequest createTestimonialRequest) {
    TestimonialResponse testimonialResponse = createTestimonial.create(createTestimonialRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(testimonialResponse);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteTestimonial.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
