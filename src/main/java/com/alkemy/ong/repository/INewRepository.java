package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.New;

import org.springframework.data.jpa.repository.JpaRepository;

public interface INewRepository extends JpaRepository<New,Long> {
    
}
