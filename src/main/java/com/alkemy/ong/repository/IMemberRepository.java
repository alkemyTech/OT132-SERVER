package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Long> {

    List<Member> findBySoftDeleteFalse();

}
