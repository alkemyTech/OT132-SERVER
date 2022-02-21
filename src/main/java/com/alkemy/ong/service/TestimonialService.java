package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.mapper.attribute.TestimonialAttributes;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.CreateTestimonialRequest;
import com.alkemy.ong.model.request.UpdateTestimonialRequest;
import com.alkemy.ong.model.response.ListTestimonialResponse;
import com.alkemy.ong.model.response.TestimonialResponse;
import com.alkemy.ong.repository.ITestimonialRepository;
import com.alkemy.ong.service.abstraction.ICreateTestimonial;
import com.alkemy.ong.service.abstraction.IDeleteTestimonial;
import com.alkemy.ong.service.abstraction.IGetTestimonialDetails;
import com.alkemy.ong.service.abstraction.IUpdateTestimonial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TestimonialService implements ICreateTestimonial, IDeleteTestimonial,
    IUpdateTestimonial, IGetTestimonialDetails {

  @Autowired
  private ITestimonialRepository testimonialRepository;

  @Autowired
  private TestimonialMapper testimonialMapper;

  @Override
  public ListTestimonialResponse findAll(Pageable pageable) {
    Page<Testimonial> page = testimonialRepository.findBySoftDeleteFalseOrderByTimestampDesc(
        pageable);
    List<TestimonialResponse> testimonialResponses = testimonialMapper.map(page.getContent());
    return buildListResponse(testimonialResponses, page);
  }

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
    Testimonial testimonial = getTestimonial(id);
    updateValues(testimonial, updateTestimonialRequest);

    return testimonialMapper.map(testimonialRepository.save(testimonial),
        TestimonialAttributes.ID,
        TestimonialAttributes.NAME,
        TestimonialAttributes.IMAGE,
        TestimonialAttributes.CONTENT);
  }

  @Override
  public void delete(Long id) {
    Testimonial testimonial = getTestimonial(id);
    testimonial.setSoftDelete(true);
    testimonialRepository.save(testimonial);
  }

  private Testimonial getTestimonial(Long id) {
    Testimonial testimonial = testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(id);
    if (testimonial == null) {
      throw new NotFoundException("Testimonial not found.");
    }
    return testimonial;
  }

  private Testimonial updateValues(Testimonial testimonial,
      UpdateTestimonialRequest updateTestimonialRequest) {
    testimonial.setName(updateTestimonialRequest.getName());
    testimonial.setImage(updateTestimonialRequest.getImage());
    testimonial.setContent(updateTestimonialRequest.getContent());
    return testimonial;
  }

  private ListTestimonialResponse buildListResponse(List<TestimonialResponse> testimonialResponses,
      Page<Testimonial> page) {
    ListTestimonialResponse listTestimonialResponse = new ListTestimonialResponse();
    listTestimonialResponse.setTestimonialResponses(testimonialResponses);
    listTestimonialResponse.setPage(page.getNumber());
    listTestimonialResponse.setSize(page.getSize());
    listTestimonialResponse.setTotalPages(page.getTotalPages());
    return listTestimonialResponse;
  }
}
