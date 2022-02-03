package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.model.response.ListCategoriesResponse;

public interface IGetCategoryDetails {

  ListCategoriesResponse findAll();

  CategoryResponse getBy(Long id);
}
