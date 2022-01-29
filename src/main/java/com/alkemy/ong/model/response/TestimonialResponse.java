package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestimonialResponse {

  private Long testimonialId;

  private String name;

  private String image;

  private String content;

  private Timestamp timestamp;

  private boolean softDelete;
}
