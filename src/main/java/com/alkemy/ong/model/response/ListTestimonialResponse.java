package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListTestimonialResponse extends PaginationResponse {

  @ApiModelProperty(value = "testimonialResponses",
      name = "Testimonial responses",
      dataType = "List",
      example = "[\"Testimonial1:[]\",\"Testimonial2:[]\",\"Testimonial3:[]\"]",
      position = 0)
  @JsonProperty("testimonials")
  List<TestimonialResponse> testimonialResponses;
}
