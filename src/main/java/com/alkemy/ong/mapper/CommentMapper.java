package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.response.CommentResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public List<CommentResponse> map(List<Comment> comments) {
    List<CommentResponse> commentResponses = new ArrayList<>(comments.size());
    for (Comment comment : comments) {
      CommentResponse commentResponse = new CommentResponse();
      commentResponse.setBody(comment.getBody());
      commentResponse.setTimestamp(comment.getTimestamp());
      commentResponses.add(commentResponse);
    }
    return commentResponses;
  }

}
