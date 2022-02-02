package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSlideRequest {

  @NotBlank
  private String file;

  private Integer order;
}
