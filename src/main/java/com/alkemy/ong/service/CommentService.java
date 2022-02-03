package com.alkemy.ong.service;

import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.service.abstraction.IGetComment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements IGetComment {

  @Autowired
  private ICommentRepository commentRepository;
  @Autowired
  private CommentMapper commentMapper;

  public ListCommentResponse findAll() {
    List<Comment> comments = commentRepository.findAllByOrderByTimestampAsc();
    List<CommentResponse> commentResponse = commentMapper.map(comments);
    return new ListCommentResponse(commentResponse);
  }

}
