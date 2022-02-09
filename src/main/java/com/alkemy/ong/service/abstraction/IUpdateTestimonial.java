package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UpdateTestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;

public interface IUpdateTestimonial {

  TestimonialResponse update(Long id, UpdateTestimonialRequest updateTestimonialRequest);
}
