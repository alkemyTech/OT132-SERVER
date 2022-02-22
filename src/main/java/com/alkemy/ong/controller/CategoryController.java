package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateCategoryRequest;
import com.alkemy.ong.model.request.UpdateCategoryRequest;
import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.model.response.ListCategoriesResponse;
import com.alkemy.ong.service.abstraction.ICreateCategory;
import com.alkemy.ong.service.abstraction.IDeleteCategory;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import com.alkemy.ong.service.abstraction.IUpdateCategory;
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
@RequestMapping("/categories")
@Api(tags = "Category Endpoints", value = "CategoryEndpoints")
public class CategoryController {

  private static final String CATEGORIES_PATH = "/categories";

  @Autowired
  private IGetCategoryDetails getCategoryDetails;

  @Autowired
  private ICreateCategory createCategory;

  @Autowired
  private PaginatedResultsRetrieved resultsRetrieved;

  @Autowired
  private IUpdateCategory updateCategory;

  @Autowired
  private IDeleteCategory deleteCategory;

  @GetMapping(produces = {"application/json"})
  @ApiOperation(value = "Return the list of categories by severous pages")
  @ApiResponses(value = {
      @ApiResponse(code = 200,
          message = "OK - The list of categories. The size of the page is the one"
          + "passed in the parameters", response = ListCategoriesResponse.class, responseHeaders = {
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
  public ResponseEntity<ListCategoriesResponse> list(Pageable pageable,
      UriComponentsBuilder uriBuilder, HttpServletResponse response) {
    ListCategoriesResponse listCategoriesResponse = getCategoryDetails.findAll(pageable);

    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(uriBuilder,
        response,
        CATEGORIES_PATH,
        listCategoriesResponse.getPage(),
        listCategoriesResponse.getTotalPages(),
        listCategoriesResponse.getSize());

    return ResponseEntity.ok().body(listCategoriesResponse);
  }

  @GetMapping(value = "/{id}",produces = {"application/json"})
  @ApiOperation(value = "Get a category's details.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The category was found and it return their details",
          response = CategoryResponse.class),
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
  public ResponseEntity<CategoryResponse> getBy(@PathVariable(value = "id") Long id) {
    CategoryResponse response = getCategoryDetails.getBy(id);
    return ResponseEntity.ok().body(response);
  }

  @PostMapping(produces = {"application/json"},
      consumes = {"application/json"})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create a category and return it.")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "CREATED - The category was successfully created",
          response = CategoryResponse.class),
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
  public ResponseEntity<CategoryResponse> create(
      @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
    CategoryResponse categoryResponse = createCategory.create(createCategoryRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
  }

  @PutMapping(value = "/{id}", produces = {"application/json"},
      consumes = {"application/json"})
  @ApiOperation(value = "Update a category passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - The category was successfully updated"),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
          + "cannot be empty or null.",
          response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Member not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the category we want to update",
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
  public ResponseEntity<CategoryResponse> update(@PathVariable("id") long id,
      @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
    return ResponseEntity.ok().body(updateCategory.update(id, updateCategoryRequest));
  }

  @DeleteMapping(value = "/{id}", produces = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete a category passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The category was successfully deleted"),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Member not found.",
          response = ErrorResponse.class)})
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the category we want to delete",
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
    deleteCategory.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
