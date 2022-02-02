package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CommentController {

  @Autowired
  private ICommentRepository commentRepository;

  @GetMapping("/comments")
  public ResponseEntity<ListCommentResponse> listComments(){
    ListCommentResponse response = commentRepository.MetodoParaTraerListado();
    return ResponseEntity.ok().body(response);
  }

}
