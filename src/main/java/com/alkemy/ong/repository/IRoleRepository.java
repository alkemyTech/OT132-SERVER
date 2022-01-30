package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
  @Query("SELECT r FROM Role r WHERE r.name = :name")
  List<Role> findByName(@Param("name") String name);

}
