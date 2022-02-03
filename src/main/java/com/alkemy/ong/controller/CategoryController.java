package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.model.response.ListCategoriesResponse;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private IGetCategoryDetails getCategoryDetails;

  @Autowired
  private PaginatedResultsRetrieved resultsRetrieved;

  @GetMapping
  public ResponseEntity<ListCategoriesResponse> list(Pageable pageable,
      UriComponentsBuilder uriBuilder, HttpServletResponse response) {
    ListCategoriesResponse listCategoriesResponse = getCategoryDetails.findAll(pageable);
    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(uriBuilder, response, "/categories",
        listCategoriesResponse.getPage(), listCategoriesResponse.getTotalPages(),
        listCategoriesResponse.getSize());
    return ResponseEntity.ok().body(listCategoriesResponse);
  }

}
