package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.mapper.attribute.CategoryAttributes;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.request.CategoryRequest;
import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.model.response.ListCategoriesResponse;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import com.alkemy.ong.service.abstraction.IUpdateCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements IGetCategoryDetails, IUpdateCategory {


  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private ICategoryRepository categoryRepository;

  @Override
  public ListCategoriesResponse findAll(Pageable pageable) {
    Page<Category> page = categoryRepository.findBySoftDeleteFalseOrderByName(pageable);
    List<CategoryResponse> categoryResponses = categoryMapper.map(page.getContent());
    return buildListResponse(categoryResponses, page);
  }

  private ListCategoriesResponse buildListResponse(List<CategoryResponse> categoryResponses,
      Page<Category> page) {
    ListCategoriesResponse listCategoriesResponse = new ListCategoriesResponse();
    listCategoriesResponse.setCategoryResponses(categoryResponses);
    listCategoriesResponse.setPage(page.getNumber());
    listCategoriesResponse.setTotalPages(page.getTotalPages());
    listCategoriesResponse.setSize(page.getSize());
    return listCategoriesResponse;
  }

  @Override
  public CategoryResponse getBy(Long id) {
    Category category = findBy(id);
    return categoryMapper.map(category,
        CategoryAttributes.CATEGORY_ID, CategoryAttributes.IMAGE, CategoryAttributes.NAME,
        CategoryAttributes.DESCRIPTION);
  }

  @Override
  public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
    Category category = findBy(id);
    category.setName(categoryRequest.getName());
    category.setDescription(categoryRequest.getDescription());
    category.setImage(categoryRequest.getImage());
    return categoryMapper.map(categoryRepository.save(category), CategoryAttributes.CATEGORY_ID,
        CategoryAttributes.IMAGE, CategoryAttributes.NAME,
        CategoryAttributes.DESCRIPTION);
  }

  private Category findBy(Long id) {
    Optional<Category> result = categoryRepository.findById(id);
    if (result.isEmpty() || result.get().isSoftDelete()) {
      throw new NotFoundException("Category could not be found.");
    }
    Category category = result.get();
    return category;
  }

}
