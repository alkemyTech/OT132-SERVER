package com.alkemy.ong.service;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.InsufficientPermissionsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.mapper.attribute.CommentAttributes;
import com.alkemy.ong.mapper.attribute.NewsAttributes;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.CreateCommentRequest;
import com.alkemy.ong.model.request.UpdateCommentRequest;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.model.response.ListCommentsInNewsResponse;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.ICreateComment;
import com.alkemy.ong.service.abstraction.IDeleteComment;
import com.alkemy.ong.service.abstraction.IGetComment;
import com.alkemy.ong.service.abstraction.IGetCommentsFromNews;
import com.alkemy.ong.service.abstraction.IUpdateComment;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements IGetComment, ICreateComment, IUpdateComment, IDeleteComment,
    IGetCommentsFromNews {

  @Autowired
  private ICommentRepository commentRepository;
  @Autowired
  private CommentMapper commentMapper;
  @Autowired
  private INewsRepository newsRepository;
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private NewsMapper newsMapper;

  @Override
  public ListCommentResponse findAll() {
    List<Comment> comments = commentRepository.findAllByOrderByTimestampAsc();
    List<CommentResponse> commentResponse = commentMapper.map(comments,
        CommentAttributes.BODY,
        CommentAttributes.TIMESTAMP);
    return new ListCommentResponse(commentResponse);
  }

  @Override
  public void create(CreateCommentRequest createCommentRequest) {
    User user = getUser(createCommentRequest.getUserId());
    News news = getNews(createCommentRequest.getNewsId());
    commentRepository.save(commentMapper.map(createCommentRequest, user, news));
  }

  @Override
  public CommentResponse update(Long id, UpdateCommentRequest updateCommentRequest,
      Authentication authentication) throws InsufficientPermissionsException {
    Comment comment = findBy(id);
    News news = getNews(updateCommentRequest.getNewsId());
    User user = getUser(updateCommentRequest.getUserId());
    checkUser(comment, authentication);
    updateValues(comment, updateCommentRequest, news, user);
    commentRepository.save(comment);
    return commentMapper.map(comment, CommentAttributes.BODY, CommentAttributes.TIMESTAMP);
  }

  @Override
  public void delete(Long id, Authentication authentication)
      throws InsufficientPermissionsException {
    Comment comment = findBy(id);
    checkUser(comment, authentication);
    commentRepository.delete(comment);
  }

  @Override
  public ListCommentsInNewsResponse list(Long id) {
    News news = getNews(id);
    List<Comment> comments = commentRepository.findByNewsId(id);
    return buildListCommentsInNewsResponse(news, comments);
  }

  private ListCommentsInNewsResponse buildListCommentsInNewsResponse(News news,
      List<Comment> comments) {
    NewsResponse newsResponse = newsMapper.map(news, NewsAttributes.NAME);
    newsResponse.setComments(commentMapper.map(comments,
        CommentAttributes.USERNAME,
        CommentAttributes.BODY,
        CommentAttributes.TIMESTAMP));

    ListCommentsInNewsResponse listCommentsInNewsResponse = new ListCommentsInNewsResponse();
    listCommentsInNewsResponse.setNews(newsResponse);
    return listCommentsInNewsResponse;
  }

  private Comment findBy(Long id) {
    Optional<Comment> result = commentRepository.findById(id);
    if (result.isEmpty()) {
      throw new NotFoundException("Comment not found");
    }
    return result.get();
  }

  private boolean isAdmin(Authentication authentication) {
    return authentication.getAuthorities().stream()
        .anyMatch(r -> RoleType.ADMIN.getFullRoleName().equals(r.getAuthority()));
  }

  private boolean isNotCreator(Comment comment, Authentication authentication) {
    return authentication.getName() != null
        && !authentication.getName().equals(comment.getUser().getEmail());
  }

  private void checkUser(Comment comment, Authentication authentication)
      throws InsufficientPermissionsException {
    if (!isAdmin(authentication) && isNotCreator(comment, authentication)) {
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

  private User getUser(Long id) {
    User user = userRepository.findByUserIdAndSoftDeleteFalse(id);
    if (user == null) {
      throw new NotFoundException("User not found.");
    }
    return user;
  }

  private News getNews(Long id) {
    News news = newsRepository.findByNewsIdAndSoftDeleteFalse(id);
    if (news == null) {
      throw new NotFoundException("Post not found.");
    }
    return news;
  }

}