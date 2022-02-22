package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsResponse {

  private Long newsId;

  private String name;

  private String text;

  private String image;

  private String categoryName;

  private List<CommentResponse> comments;

}
