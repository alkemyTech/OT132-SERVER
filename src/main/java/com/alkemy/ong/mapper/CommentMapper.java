package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.CreateCommentRequest;
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

  public Comment map(CreateCommentRequest createCommentRequest, User user, News news) {
    Comment comment = new Comment();
    comment.setBody(createCommentRequest.getBody());
    comment.setNews(news);
    comment.setUser(user);
    return comment;
  }
}
