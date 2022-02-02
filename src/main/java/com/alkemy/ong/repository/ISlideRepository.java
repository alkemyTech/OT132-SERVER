package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlideRepository extends JpaRepository<Slide, Long> {

  @Query(value = "select MAX(slide_order) from Slides", nativeQuery = true)
  public Integer getMaxOrder();

}
