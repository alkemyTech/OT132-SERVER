package com.alkemy.ong.model.request;

import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestimonialRequest {

  private Long testimonialId;

  @NotBlank(message = "the name field cannot be empty")
  private String name;

  private String image;

  @NotBlank(message = "the content field cannot be empty")
  private String content;

  private Timestamp timestamp;

  private boolean softDelete;
}
