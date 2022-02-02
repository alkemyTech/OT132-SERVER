package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import org.springframework.lang.Nullable;

@Getter
@Setter
public class CreateSlideRequest {

  @NotBlank(message = "This text cannot be empty")
  private String fileEncodeBase64;

  @Nullable
  private Integer order;
}
