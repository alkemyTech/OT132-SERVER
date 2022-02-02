package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.mapper.attribute.CategoryAttributes;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.model.response.ListCategoriesResponse;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements IGetCategoryDetails {

  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private ICategoryRepository categoryRepository;

  @Override
  public ListCategoriesResponse findAll() {
    List<Category> categories = categoryRepository.findBySoftDeleteFalseOrderByName();
    List<CategoryResponse> categoryResponses = categoryMapper.map(categories);
    return new ListCategoriesResponse(categoryResponses);
  }

  @Override
  public CategoryResponse getBy(Long id) {
    Optional<Category> result = categoryRepository.findById(id);
    if (result.isEmpty() || result.get().isSoftDelete()) {
      throw new NotFoundException("Category could not be found.");
    } else {
      return categoryMapper.map(result.get(),
          CategoryAttributes.CATEGORY_ID, CategoryAttributes.IMAGE, CategoryAttributes.NAME,
          CategoryAttributes.DESCRIPTION);
    }
  }
}
