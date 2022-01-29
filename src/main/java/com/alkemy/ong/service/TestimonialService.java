package com.alkemy.ong.service;

import com.alkemy.ong.exception.EmptyInputException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.mapper.attribute.TestimonialAttributes;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.TestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import com.alkemy.ong.repository.ITestimonialRepository;
import com.alkemy.ong.service.abstraction.IPostTestimonial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialService implements IPostTestimonial {

  @Autowired
  private ITestimonialRepository testimonialRepository;
  @Autowired
  private TestimonialMapper testimonialMapper;

  @Override
  public TestimonialResponse create(TestimonialRequest testimonialRequest) {
    Testimonial testimonial = null;
    testimonial = testimonialMapper.mapAdd(testimonialRequest);
    testimonialRepository.save(testimonial);
    TestimonialResponse testimonialResponse = testimonialMapper.map(testimonial,
        TestimonialAttributes.ID, TestimonialAttributes.CONTENT, TestimonialAttributes.IMAGE,
        TestimonialAttributes.SOFTDELETE, TestimonialAttributes.NAME,
        TestimonialAttributes.TIMESTAMP);
    return testimonialResponse;
  }
}
