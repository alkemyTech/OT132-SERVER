package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CreateCategoryRequest;
import com.alkemy.ong.model.response.CategoryResponse;

public interface IUpdateCategory {

  CategoryResponse update(Long id, CreateCategoryRequest categoryRequest);
}
