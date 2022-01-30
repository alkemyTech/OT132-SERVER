package com.alkemy.ong.service;

import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.mapper.attribute.TestimonialAttributes;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.TestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import com.alkemy.ong.repository.ITestimonialRepository;
import com.alkemy.ong.service.abstraction.ICreateTestimonial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialService implements ICreateTestimonial {

  @Autowired
  private ITestimonialRepository testimonialRepository;
  @Autowired
  private TestimonialMapper testimonialMapper;

  @Override
  public TestimonialResponse create(TestimonialRequest testimonialRequest) {
    Testimonial testimonial = testimonialMapper.map(testimonialRequest);
    testimonial.setSoftDelete(false);
    testimonialRepository.save(testimonial);
    return testimonialMapper.map(testimonial,
        TestimonialAttributes.CONTENT, TestimonialAttributes.IMAGE,
        TestimonialAttributes.NAME);
  }
}
