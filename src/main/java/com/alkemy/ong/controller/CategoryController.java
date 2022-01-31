package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.ListCategoriesResponse;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private IGetCategoryDetails getCategoryDetails;

  @GetMapping
  public ResponseEntity<ListCategoriesResponse> list() {
    ListCategoriesResponse response = getCategoryDetails.findAll();
    return ResponseEntity.ok().body(response);
  }

}
