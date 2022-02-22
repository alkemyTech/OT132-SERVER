package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.attribute.CommentAttributes;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.CreateCommentRequest;
import com.alkemy.ong.model.response.CommentResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public List<CommentResponse> map(List<Comment> comments, CommentAttributes... commentAttributes) {
    List<CommentResponse> commentResponses = new ArrayList<>(comments.size());
    for (Comment comment : comments) {
      commentResponses.add(map(comment, commentAttributes));
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

  public CommentResponse map(Comment comment, CommentAttributes... commentAttributes) {
    CommentResponse commentResponse = new CommentResponse();
    for (CommentAttributes commentAttribute : commentAttributes) {
      switch (commentAttribute) {
        case USERNAME:
          commentResponse.setUsername(comment.getUser().getUsername());
          break;
        case BODY:
          commentResponse.setBody(comment.getBody());
          break;
        case TIMESTAMP:
          commentResponse.setTimestamp(comment.getTimestamp());
          break;
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("Comment attribute: {0} is unsupported", commentAttribute));
      }
    }
    return commentResponse;
  }

}
