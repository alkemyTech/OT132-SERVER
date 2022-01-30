package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CreateTestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;

public interface ICreateTestimonial {

  TestimonialResponse create(CreateTestimonialRequest createTestimonialRequest);
}
