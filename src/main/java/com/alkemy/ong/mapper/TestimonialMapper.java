package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.attribute.TestimonialAttributes;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.CreateTestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

  public Testimonial map(CreateTestimonialRequest createTestimonialRequest) {
    Testimonial testimonial = new Testimonial();
    testimonial.setContent(createTestimonialRequest.getContent());
    testimonial.setImage(createTestimonialRequest.getImage());
    testimonial.setName(createTestimonialRequest.getName());
    return testimonial;
  }

  public TestimonialResponse map(Testimonial testimonial,
      TestimonialAttributes... testimonialAttributes) {
    TestimonialResponse testimonialResponse = new TestimonialResponse();
    for (TestimonialAttributes testimonialAttribute : testimonialAttributes) {
      switch (testimonialAttribute) {
        case ID:
          testimonialResponse.setTestimonialsId(testimonial.getTestimonialId());
          break;
        case NAME:
          testimonialResponse.setName(testimonial.getName());
          break;
        case CONTENT:
          testimonialResponse.setContent(testimonial.getContent());
          break;
        case IMAGE:
          testimonialResponse.setImage(testimonial.getImage());
          break;
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("Testimonial attribute: {0} is unsupported",
                  testimonialAttribute));
      }
    }
    return testimonialResponse;
  }

  public List<TestimonialResponse> map(List<Testimonial> testimonials) {
    List<TestimonialResponse> testimonialResponses = new ArrayList<>(testimonials.size());
    for (Testimonial testimonial : testimonials) {
      testimonialResponses.add(map(testimonial, TestimonialAttributes.ID,
          TestimonialAttributes.NAME, TestimonialAttributes.IMAGE, TestimonialAttributes.CONTENT));
    }
    return testimonialResponses;
  }
}
