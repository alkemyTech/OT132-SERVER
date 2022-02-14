package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.CreateCommentRequest;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.ICreateComment;
import com.alkemy.ong.service.abstraction.IGetComment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements IGetComment, ICreateComment {

  @Autowired
  private ICommentRepository commentRepository;
  @Autowired
  private CommentMapper commentMapper;
  @Autowired
  private INewsRepository newsRepository;
  @Autowired
  private IUserRepository userRepository;

  public ListCommentResponse findAll() {
    List<Comment> comments = commentRepository.findAllByOrderByTimestampAsc();
    List<CommentResponse> commentResponse = commentMapper.map(comments);
    return new ListCommentResponse(commentResponse);
  }

  @Override
  public void create(CreateCommentRequest createCommentRequest) {
    User user = userRepository.findByUserIdAndSoftDeleteFalse(createCommentRequest.getUserId());
    if (user == null) {
      throw new NotFoundException("User not found.");
    }

    News news = newsRepository.findByNewsIdAndSoftDeleteFalse(createCommentRequest.getNewsId());
    if (news == null) {
      throw new NotFoundException("Post not found.");
    }
    commentRepository.save(commentMapper.map(createCommentRequest, user, news));
  }

}
