package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.exception.InsufficientPermissionsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateCommentRequest;
import com.alkemy.ong.model.request.UpdateCommentRequest;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.model.response.ListCommentResponse;
import com.alkemy.ong.service.abstraction.ICreateComment;
import com.alkemy.ong.service.abstraction.IDeleteComment;
import com.alkemy.ong.service.abstraction.IGetComment;
import com.alkemy.ong.service.abstraction.IUpdateComment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@Api(tags = "Comment Endpoints", value = "CommentEndpoints")
public class CommentController {

  @Autowired
  private IGetComment getComment;

  @Autowired
  private ICreateComment createComment;

  @Autowired
  private IUpdateComment updateComment;

  @Autowired
  private IDeleteComment deleteComment;

  @GetMapping(produces = {"application/json"})
  @ApiOperation(value = "Return the list of comments")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The list of comments.",
          response = ListCommentResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass =
              String.class,
          example =
              "Bearer access_token")})
  public ResponseEntity<ListCommentResponse> list() {
    ListCommentResponse listResponse = getComment.findAll();
    return ResponseEntity.ok().body(listResponse);
  }

  @PostMapping(consumes = {"application/json"},
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Create a comment")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The comment was successfully created"),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)})
  @ApiImplicitParam(name = "Authorization", value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public ResponseEntity<Void> create(@RequestBody @Valid CreateCommentRequest createCommentRequest)
      throws NotFoundException {
    createComment.create(createCommentRequest);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping(value = "/{id}", consumes = {"application/json"},
      produces = {"application/json"})
  @ApiOperation(value = "Update a comment passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The comment was successfully updated",
          response = CommentResponse.class),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Comment not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id", value = "Id of the comment we want to update",
          required = true, allowEmptyValue = false,
          paramType = "path", dataTypeClass = String.class,
          example = "1"),
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")})
  public ResponseEntity<CommentResponse> update(@PathVariable Long id,
      @Valid @RequestBody UpdateCommentRequest updateCommentRequest, Authentication authentication)
      throws InsufficientPermissionsException {
    CommentResponse commentResponse = updateComment.update(id, updateCommentRequest,
        authentication);
    return ResponseEntity.ok().body(commentResponse);
  }

  @DeleteMapping(value = "/{id}", produces = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete a comment passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The comment was successfully deleted"),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Comment not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the comment we want to delete",
          required = true, allowEmptyValue = false,
          paramType = "path", dataTypeClass = String.class,
          example = "1"),
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")})
  public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication)
      throws InsufficientPermissionsException {
    deleteComment.delete(id, authentication);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
