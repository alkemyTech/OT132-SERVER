package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.request.UpdateNewsRequest;
import com.alkemy.ong.model.response.ListCommentsInNewsResponse;
import com.alkemy.ong.model.response.ListNewsResponse;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.service.abstraction.ICreateNews;
import com.alkemy.ong.service.abstraction.IDeleteNews;
import com.alkemy.ong.service.abstraction.IGetCommentsFromNews;
import com.alkemy.ong.service.abstraction.IGetNewsDetails;
import com.alkemy.ong.service.abstraction.IUpdateNews;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("news")
@Api(tags = "New Endpoints", value = "NewEndpoints")
public class NewsController {

  private static final String NEWS_PATH = "/news";

  @Autowired
  private ICreateNews createNews;

  @Autowired
  private IDeleteNews deleteNews;

  @Autowired
  private IGetNewsDetails getNewsDetails;

  @Autowired
  private PaginatedResultsRetrieved resultsRetrieved;

  @Autowired
  private IUpdateNews updateNews;

  @Autowired
  private IGetCommentsFromNews getCommentsFromNews;

  @GetMapping(produces = {"application/json"})
  @ApiOperation(value = "Return the list of news by severous pages")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The list of news. The size of the page is the one"
          + "passed in the parameters", response = ListNewsResponse.class, responseHeaders = {
          @ResponseHeader(name = "Link",
              description = "Link of the previous page and another for the next page",
              response = String.class)}),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "page", value = "Page of the list",
          required = true,
          paramType = "query",
          dataTypeClass = String.class,
          example = "0"),
      @ApiImplicitParam(name = "size",
          value = "Size of the page",
          required = false,
          paramType = "query",
          dataTypeClass = String.class,
          example = "10"),
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")})
  public ResponseEntity<ListNewsResponse> list(Pageable pageable, UriComponentsBuilder uriBuilder,
      HttpServletResponse response) {
    ListNewsResponse listNewsResponse = getNewsDetails.findAll(pageable);

    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(uriBuilder,
        response, NEWS_PATH,
        listNewsResponse.getPage(),
        listNewsResponse.getTotalPages(),
        listNewsResponse.getSize());

    return ResponseEntity.ok(listNewsResponse);
  }

  @GetMapping(value = "/{id}/comments", produces = {"application/json"})
  @ApiOperation(value = "Return a list of comment passed by news id.")
  @ApiResponses(value = {
      @ApiResponse(code = 200,
          message = "OK - The news was found and it return the list of comment"),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - News not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the news we want to get their comments",
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
  public ResponseEntity<ListCommentsInNewsResponse> listCommentsBy(
      @PathVariable(value = "id") Long id)
      throws NotFoundException {
    ListCommentsInNewsResponse listCommentsInNewsResponse = getCommentsFromNews.list(id);
    return ResponseEntity.ok().body(listCommentsInNewsResponse);
  }

  @DeleteMapping(value = "/{id}", produces = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete a news passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The news was successfully deleted"),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - News not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the news we want to delete",
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
  public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
    deleteNews.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(produces = {"application/json"},
      consumes = {"application/json"})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create a news and return it.")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "CREATED - The news was successfully created",
          response = MemberResponse.class),
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
  public ResponseEntity<NewsResponse> create(
      @RequestBody @Valid CreateNewsRequest createNewsRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(createNews.create(createNewsRequest));
  }

  @PutMapping(value = "/{id}",consumes = {"application/json"},
      produces = {"application/json"})
  @ApiOperation(value = "Update a news passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The news was successfully updated"),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - News not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the news we want to update",
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
  public ResponseEntity<NewsResponse> update(@PathVariable(value = "id") Long id,
      @RequestBody @Valid UpdateNewsRequest updateNewsRequest) throws NotFoundException {
    NewsResponse newsResponse = updateNews.update(id, updateNewsRequest);
    return ResponseEntity.status(HttpStatus.OK).body(newsResponse);
  }

  @GetMapping(value = "/{id}", produces = {"application/json"})
  @ApiOperation(value = "Return a news details passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The news was found and it return their details"),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - News not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the news we want to get their details",
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
  public ResponseEntity<NewsResponse> getBy(@PathVariable(value = "id") Long id)
      throws NotFoundException {
    return ResponseEntity.ok().body(getNewsDetails.getBy(id));
  }
}
