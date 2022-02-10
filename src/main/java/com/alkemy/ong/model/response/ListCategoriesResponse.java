package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoriesResponse extends PaginationResponse{

  @JsonProperty("categories")
  private List<CategoryResponse> categoryResponses;
  
}
