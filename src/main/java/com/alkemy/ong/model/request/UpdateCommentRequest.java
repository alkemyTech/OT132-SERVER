package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequest {

  @NotNull(message = "The user ID cannot be null or empty.")
  private Long userId;

  @NotNull(message = "The news ID cannot be null or empty.")
  private Long newsId;

  @NotBlank(message = "The body cannot be null or empty.")
  private String body;
}
