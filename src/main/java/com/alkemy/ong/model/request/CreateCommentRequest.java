package com.alkemy.ong.model.request;

import java.security.Timestamp;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {

  @NotBlank(message = "The user ID cannot be null or empty.")
  private Long userId;

  @NotBlank(message = "The news ID cannot be null or empty.")
  private Long newsId;

  @NotBlank(message = "The body cannot be null or empty.")
  private String body;

  private Timestamp timestamp;
}
