package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestimonialResponse {

  private Long testimonialsId;

  private String name;

  private String image;

  private String content;


}
