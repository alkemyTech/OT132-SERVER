package com.alkemy.ong.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSlideRequest {

  private String imageUrl;
  private int order;
  private String text;
}
