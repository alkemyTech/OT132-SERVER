package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.response.CategoryResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public List<CategoryResponse> map(List<Category> categories) {

    List<CategoryResponse> categoryResponseList = new ArrayList<>();

    for (Category category : categories) {
      CategoryResponse categoryResponse = new CategoryResponse();
      categoryResponse.setName(category.getName());
      categoryResponseList.add(categoryResponse);
    }

    return categoryResponseList;
  }
}
