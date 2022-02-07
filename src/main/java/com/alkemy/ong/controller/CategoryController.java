package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.model.response.ListCategoriesResponse;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  private PaginatedResultsRetrieved resultsRetrieved;

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
}