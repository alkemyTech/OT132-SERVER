package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.TestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;

public interface IPostTestimonial {

  public TestimonialResponse create(TestimonialRequest testimonialRequest);
}
