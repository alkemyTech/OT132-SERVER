package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.model.response.ListCategoriesResponse;
import org.springframework.data.domain.Pageable;

public interface IGetCategoryDetails {

  ListCategoriesResponse findAll(Pageable pageable);

  CategoryResponse getBy(Long id);
}
