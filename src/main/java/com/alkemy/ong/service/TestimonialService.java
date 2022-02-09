package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.mapper.attribute.TestimonialAttributes;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.CreateTestimonialRequest;
import com.alkemy.ong.model.request.UpdateTestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import com.alkemy.ong.repository.ITestimonialRepository;
import com.alkemy.ong.service.abstraction.ICreateTestimonial;
import com.alkemy.ong.service.abstraction.IDeleteTestimonial;
import com.alkemy.ong.service.abstraction.IUpdateTestimonial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialService implements ICreateTestimonial, IDeleteTestimonial,
    IUpdateTestimonial {

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

  @Override
  public TestimonialResponse update(Long id, UpdateTestimonialRequest updateTestimonialRequest) {

    Testimonial testimonial = testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(id);

    if (testimonial == null) {
      throw new NotFoundException("Testimonial not found");
    }

    update(testimonial, updateTestimonialRequest);

    return testimonialMapper.map(testimonialRepository.save(testimonial),
        TestimonialAttributes.ID,
        TestimonialAttributes.NAME,
        TestimonialAttributes.IMAGE,
        TestimonialAttributes.CONTENT);
  }

  @Override
  public void delete(Long id) {
    Testimonial testimonial = testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(id);

    if (testimonial == null) {
      throw new NotFoundException("Testimonial not found");
    }

    testimonial.setSoftDelete(true);
    testimonialRepository.save(testimonial);
  }

  private Testimonial update(Testimonial testimonial,
      UpdateTestimonialRequest updateTestimonialRequest) {
    testimonial.setName(updateTestimonialRequest.getName());
    testimonial.setImage(updateTestimonialRequest.getImage());
    testimonial.setContent(updateTestimonialRequest.getContent());
    return testimonial;
  }

}
