package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.CategoryResponse;
import java.util.List;

public interface IGetCategoryDetails {

	List<CategoryResponse> findAllCategories();
}
