package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.InsufficientPermissionsException;
import com.alkemy.ong.model.request.UpdateCommentRequest;
import com.alkemy.ong.model.response.CommentResponse;
import org.springframework.security.core.Authentication;

public interface IUpdateComment {

  CommentResponse update(Long id, UpdateCommentRequest updateCommentRequest,
      Authentication authentication) throws InsufficientPermissionsException;
}
