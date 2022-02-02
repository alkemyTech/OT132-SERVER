package com.alkemy.ong.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListCommentResponse {

  @JsonProperty("comment")
  private List<CommentResponse> commentResponses;

}
