package com.alkemy.ong.model.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListNewsResponse {

  @JsonProperty("News")
  private List<NewsResponse> newsResponse;
  private int page;
  private int totalPages;
  private int size;
      
}
