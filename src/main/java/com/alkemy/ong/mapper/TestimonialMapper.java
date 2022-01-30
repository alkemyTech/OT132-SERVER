package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.attribute.TestimonialAttributes;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.model.request.TestimonialRequest;
import com.alkemy.ong.model.response.TestimonialResponse;
import java.text.MessageFormat;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

  public Testimonial map(TestimonialRequest testimonialRequest) {
    Testimonial testimonial = new Testimonial();
    testimonial.setContent(testimonialRequest.getContent());
    testimonial.setImage(testimonialRequest.getImage());
    testimonial.setName(testimonialRequest.getName());
    return testimonial;
  }

  public TestimonialResponse map(Testimonial testimonial,
      TestimonialAttributes... testimonialAttributes) {
    TestimonialResponse testimonialResponse = new TestimonialResponse();
    for (TestimonialAttributes testimonialAttribute : testimonialAttributes) {
      switch (testimonialAttribute) {
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
}
