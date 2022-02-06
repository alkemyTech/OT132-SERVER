package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlideRepository extends JpaRepository<Slide, Long> {

  @Query("SELECT COALESCE(MAX(s.order),0) FROM Slide s")
  Integer getMaxOrder();

  Slide deleteSlidesBySlideId(Long id);
}
