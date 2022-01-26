package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.response.CategoryResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public List<CategoryResponse> map(List<Category> categories) {

    // Sort List. Add comparator
    List<CategoryResponse> categoryResponseList = new ArrayList<>();
    CategoryResponse categoryResponse = new CategoryResponse();

    for (Category aux : categories) {
      categoryResponse.setName(aux.getName());
      categoryResponseList.add(categoryResponse);
    }

    return categoryResponseList;
  }
}
