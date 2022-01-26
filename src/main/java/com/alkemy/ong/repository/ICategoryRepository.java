package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT c FROM Category c WHERE c.SOFT_DELETE = :false ORDER BY name")
    public List<Category> findAllActiveCategories();
	
}
