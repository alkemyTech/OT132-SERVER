package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.attribute.CategoryAttributes;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.request.CreateCategoryRequest;
import com.alkemy.ong.model.response.CategoryResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public CategoryResponse map(Category category, CategoryAttributes... categoryAttributes) {
    CategoryResponse categoryResponse = new CategoryResponse();
    for (CategoryAttributes categoryAttribute : categoryAttributes) {
      switch (categoryAttribute) {
        case CATEGORY_ID:
          categoryResponse.setCategoryId(category.getCategoryId());
          break;
        case NAME:
          categoryResponse.setName(category.getName());
          break;
        case IMAGE:
          categoryResponse.setImage(category.getImage());
          break;
        case DESCRIPTION:
          categoryResponse.setDescription(category.getDescription());
          break;
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("Category attribute: {0} is unsupported", categoryAttribute));
      }
    }
    return categoryResponse;
  }

  public List<CategoryResponse> map(List<Category> categories) {
    List<CategoryResponse> categoryResponses = new ArrayList<>(categories.size());
    for (Category category : categories) {
      categoryResponses.add(map(category, CategoryAttributes.NAME));
    }
    return categoryResponses;
  }

  public Category map(CreateCategoryRequest createCategoryRequest) {
    Category category = new Category();
    category.setName(createCategoryRequest.getName());
    category.setDescription(createCategoryRequest.getDescription());
    category.setImage(createCategoryRequest.getImage());
    return category;
  }
}
