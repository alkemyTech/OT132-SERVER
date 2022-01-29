package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.response.CategoryResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public List<CategoryResponse> listMap(List<Category> categories) {

    List<CategoryResponse> categoryResponseList = new ArrayList<>();

    for (Category aux : categories) {
      CategoryResponse categoryResponse = new CategoryResponse();
      categoryResponse.setName(aux.getName());
      categoryResponseList.add(categoryResponse);
    }

    return categoryResponseList;
  }
}
