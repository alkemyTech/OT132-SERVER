package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.mapper.attribute.CategoryAttributes;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.CreateCategoryRequest;
import com.alkemy.ong.model.request.UpdateCategoryRequest;
import com.alkemy.ong.model.response.CategoryResponse;
import com.alkemy.ong.model.response.ListCategoriesResponse;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.abstraction.ICreateCategory;
import com.alkemy.ong.service.abstraction.IDeleteCategory;
import com.alkemy.ong.service.abstraction.IGetCategoryDetails;
import com.alkemy.ong.service.abstraction.IUpdateCategory;
import com.amazonaws.services.workdocs.model.EntityNotExistsException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements IGetCategoryDetails, ICreateCategory, IUpdateCategory, IDeleteCategory {

  private static final String ERROR_MESSAGE = "Error category not found.";



  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private ICategoryRepository categoryRepository;

  @Autowired
  private IDeleteCategory deleteCategory;

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
  public CategoryResponse update(Long id, UpdateCategoryRequest updateCategoryRequest) {
    Category category = findBy(id);
    category.setName(updateCategoryRequest.getName());
    category.setDescription(updateCategoryRequest.getDescription());
    category.setImage(updateCategoryRequest.getImage());
    return categoryMapper.map(categoryRepository.save(category), CategoryAttributes.CATEGORY_ID,
        CategoryAttributes.IMAGE, CategoryAttributes.NAME,
        CategoryAttributes.DESCRIPTION);
  }

  private Category findBy(Long id) {
    Optional<Category> result = categoryRepository.findById(id);
    if (result.isEmpty() || result.get().isSoftDelete()) {
      throw new NotFoundException("Category could not be found.");
    }
    return result.get();
  }

  @Override
  public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {
    Category category = categoryMapper.map(createCategoryRequest);
    category.setSoftDelete(false);
    categoryRepository.save(category);
    return categoryMapper.map(category, CategoryAttributes.CATEGORY_ID, CategoryAttributes.NAME,
        CategoryAttributes.IMAGE, CategoryAttributes.DESCRIPTION);
  }
  @Override
  public void delete(Long id) {
    Category category = findBy(id);
        category.setSoftDelete(true);
        categoryRepository.save(category);
    }
}
