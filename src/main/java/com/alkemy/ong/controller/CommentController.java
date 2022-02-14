package com.alkemy.ong.controller;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateCommentRequest;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.service.abstraction.ICreateComment;
import com.alkemy.ong.service.abstraction.IGetComment;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

  @Autowired
  private IGetComment getComment;

  @Autowired
  private ICreateComment createComment;

  @GetMapping
  public ResponseEntity<ListCommentResponse> list() {
    ListCommentResponse listResponse = getComment.findAll();
    return ResponseEntity.ok().body(listResponse);
  }

  @PostMapping
  public ResponseEntity<Void> create(
      @RequestBody @Valid CreateCommentRequest createCommentRequest) throws NotFoundException {

    createComment.create(createCommentRequest);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
