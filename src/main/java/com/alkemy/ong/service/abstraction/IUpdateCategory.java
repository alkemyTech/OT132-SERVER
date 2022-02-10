package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UpdateCategoryRequest;
import com.alkemy.ong.model.response.CategoryResponse;

public interface IUpdateCategory {

  CategoryResponse update(Long id, UpdateCategoryRequest updateCategoryRequest);
}
