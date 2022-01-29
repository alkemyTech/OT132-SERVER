package com.alkemy.ong.mapper;

import com.alkemy.ong.exception.EmptyInputException;
import com.alkemy.ong.mapper.attribute.TestimonialAttributes;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.TestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import java.sql.Timestamp;
import java.text.MessageFormat;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

  public Testimonial mapAdd(TestimonialRequest testimonialRequest) {
    if (testimonialRequest.getName().isEmpty() || testimonialRequest.getName() == null) {
      throw new EmptyInputException("the name field cannot be empty");
    }
    if (testimonialRequest.getContent().isEmpty() || testimonialRequest.getContent() == null) {
      throw new EmptyInputException("the content field cannot be empty");
    }
    Testimonial testimonial = new Testimonial();
    testimonial.setTestimonialId(testimonialRequest.getTestimonialId());
    testimonial.setContent(testimonialRequest.getContent());
    testimonial.setImage(testimonialRequest.getImage());
    testimonial.setName(testimonialRequest.getName());
    testimonial.setTimestamp(new Timestamp(System.currentTimeMillis()));
    testimonial.setSoftDelete(false);
    return testimonial;
  }

  public TestimonialResponse map(Testimonial testimonial,
      TestimonialAttributes... testimonialAttributes) {
    TestimonialResponse testimonialResponse = new TestimonialResponse();
    for (TestimonialAttributes testimonialAttribute : testimonialAttributes) {
      switch (testimonialAttribute) {
        case ID:
          testimonialResponse.setTestimonialId(testimonial.getTestimonialId());
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
        case TIMESTAMP:
          testimonialResponse.setTimestamp(testimonial.getTimestamp());
          break;
        case SOFTDELETE:
          testimonialResponse.setSoftDelete(testimonial.isSoftDelete());
          break;
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("Slide attribute: {0} is unsupported", testimonialAttribute));
      }
    }
    return testimonialResponse;
  }
}
