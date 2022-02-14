package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsResponse {

  private Long newsId;

  private String name;

  private String text;

  private String image;

  private String categoryName;

}
