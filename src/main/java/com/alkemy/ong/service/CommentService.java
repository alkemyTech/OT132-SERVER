package com.alkemy.ong.service;

import com.alkemy.ong.exception.InsufficientPermissionsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.CreateCommentRequest;
import com.alkemy.ong.model.request.UpdateCommentRequest;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.ICreateComment;
import com.alkemy.ong.service.abstraction.IGetComment;
import com.alkemy.ong.service.abstraction.IUpdateComment;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements IGetComment, ICreateComment,
    IUpdateComment {

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

  @Override
  public CommentResponse update(Long id, UpdateCommentRequest updateCommentRequest,
      Authentication authentication) throws InsufficientPermissionsException {
    Comment comment = findBy(id);
    News news = newsRepository.findByNewsIdAndSoftDeleteFalse(updateCommentRequest.getNewsId());
    User user = userRepository.findByUserIdAndSoftDeleteFalse(updateCommentRequest.getUserId());
    checkUser(comment, authentication);
    updateValues(comment, updateCommentRequest, news, user);
    commentRepository.save(comment);
    return commentMapper.map(comment);
  }

  private Comment findBy(Long id) {
    Optional<Comment> result = commentRepository.findById(id);
    if (result.isEmpty()) {
      throw new NotFoundException("Comment not found");
    }
    return result.get();
  }

  private void checkUser(Comment comment, Authentication authentication)
      throws InsufficientPermissionsException {
    if (!authentication.getName().equals(comment.getUser().getEmail())) {
      throw new InsufficientPermissionsException("Unauthorized to do changes");
    }
  }

  private void updateValues(Comment comment, UpdateCommentRequest updateCommentRequest, News news,
      User user) {
    comment.setBody(updateCommentRequest.getBody());
    comment.setNews(news);
    comment.setUser(user);
    comment.setTimestamp(Timestamp.from(Instant.now()));
  }
}
