package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.response.CategoryResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public List<CategoryResponse> map(List<Category> categories) {

    List<CategoryResponse> response = new ArrayList<>(categories.size());

    for (Category category : categories) {
      CategoryResponse categoryResponse = new CategoryResponse();
      categoryResponse.setName(category.getName());
      response.add(categoryResponse);
    }

    return response;
  }
}
