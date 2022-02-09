package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CreateCategoryRequest;
import com.alkemy.ong.model.response.CategoryResponse;

public interface ICreateCategory {

  CategoryResponse create(CreateCategoryRequest createCategoryRequest);
    
}
