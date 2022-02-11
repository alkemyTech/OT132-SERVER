package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestimonialRepository extends JpaRepository<Testimonial, Long> {

  Testimonial findByTestimonialIdAndSoftDeleteFalse(Long id);

  Page<Testimonial> findAll(Pageable pageable);
}
