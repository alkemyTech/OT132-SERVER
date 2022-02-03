package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.service.abstraction.IGetComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CommentController {

  @Autowired
  private IGetComment getComment;

  @GetMapping("/comments")
  public ResponseEntity<ListCommentResponse> list() {
    ListCommentResponse listResponse = getComment.findAll();
    return ResponseEntity.ok().body(listResponse);
  }
}
