package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CreateCommentRequest;

public interface ICreateComment {

  void create(CreateCommentRequest createCommentRequest);
}
