package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Member;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Long> {

  List<Member> findBySoftDeleteFalse();

  Page<Member> findAll(Pageable pageable);

  Page<Member> findBySoftDeleteFalseOrderByTimestampDesc(Pageable pageable);

}