package com.alkemy.ong.model.request;

import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TestimonialRequest {

  @NotBlank
  private Long testimonialId;

  @NotBlank
  private String name;

  private String image;

  @NotBlank
  private String content;

  private Timestamp timestamp;

  private boolean softDelete;
}
