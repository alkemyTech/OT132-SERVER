package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateCategoryRequest;
import com.alkemy.ong.model.request.UpdateCategoryRequest;
import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.model.response.ListCategoriesResponse;
import com.alkemy.ong.service.abstraction.ICreateCategory;
import com.alkemy.ong.service.abstraction.IDeleteCategory;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import com.alkemy.ong.service.abstraction.IUpdateCategory;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categories")
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

  @GetMapping
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

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getBy(@PathVariable(value = "id") Long id) {
    CategoryResponse response = getCategoryDetails.getBy(id);
    return ResponseEntity.ok().body(response);
  }

  @PostMapping
  public ResponseEntity<CategoryResponse> create(
      @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
    CategoryResponse categoryResponse = createCategory.create(createCategoryRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponse> update(@PathVariable("id") long id,
      @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
    return ResponseEntity.ok().body(updateCategory.update(id, updateCategoryRequest));
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
    deleteCategory.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
