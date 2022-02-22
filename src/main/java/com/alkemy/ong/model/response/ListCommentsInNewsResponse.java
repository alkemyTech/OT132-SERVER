package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListCommentsInNewsResponse extends PaginationResponse {

  private String newsName;

  @JsonProperty("comment")
  private List<CommentResponse> commentResponses;

}
