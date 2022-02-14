package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

  Page<Category> findBySoftDeleteFalseOrderByName(Pageable pageable);

  Category findByNameIgnoreCase(String name);

  Category findByCategoryIdAndSoftDeleteFalse(Long id);

}
