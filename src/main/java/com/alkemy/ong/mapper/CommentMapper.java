package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.CreateCommentRequest;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.model.response.ListCommentsInNewsResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public List<CommentResponse> map(List<Comment> comments) {
    List<CommentResponse> commentResponses = new ArrayList<>(comments.size());
    for (Comment comment : comments) {
      commentResponses.add(map(comment));
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

  public CommentResponse map(Comment comment) {
    CommentResponse commentResponse = new CommentResponse();
    commentResponse.setBody(comment.getBody());
    commentResponse.setTimestamp(comment.getTimestamp());
    return commentResponse;
  }

  public ListCommentsInNewsResponse map(ListCommentResponse listCommentResponse, News news) {
    ListCommentsInNewsResponse listCommentsInNewsResponse = new ListCommentsInNewsResponse();
    listCommentsInNewsResponse.setNewsName(news.getName());
    listCommentsInNewsResponse.setCommentResponses(listCommentResponse.getCommentResponses());
    return listCommentsInNewsResponse;
  }
}
