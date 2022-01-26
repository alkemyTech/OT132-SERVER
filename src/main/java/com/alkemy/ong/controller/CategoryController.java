package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired private IGetCategoryDetails getCategoryDetails;
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryResponse>> getListOfCategories(){
		
		List<CategoryResponse> categoryList = getCategoryDetails.findAllCategories();
		
		return ResponseEntity.ok().body(categoryList);
	}
}
