package com.alkemy.ong.service;

import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.mapper.attribute.TestimonialAttributes;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.CreateTestimonialRequest;
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
  public TestimonialResponse create(CreateTestimonialRequest createTestimonialRequest) {
    Testimonial testimonial = testimonialMapper.map(createTestimonialRequest);
    testimonial.setSoftDelete(false);
    return testimonialMapper.map(testimonialRepository.save(testimonial),
        TestimonialAttributes.ID,
        TestimonialAttributes.CONTENT,
        TestimonialAttributes.IMAGE,
        TestimonialAttributes.NAME);
  }
}
