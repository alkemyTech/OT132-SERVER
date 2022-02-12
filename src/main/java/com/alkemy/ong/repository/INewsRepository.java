package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INewsRepository extends JpaRepository<News, Long> {

  News findByNewsIdAndSoftDeleteFalse(Long id);

  Page<News> findBySoftDeleteFalseOrderByTimestampDesc(Pageable pageable);

}
