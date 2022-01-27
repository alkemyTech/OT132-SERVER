package com.alkemy.ong.repository;

import java.util.List;
import com.alkemy.ong.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {

    @Query("Select c from Contact c where c.deletedAt = null")
    List<Contact> findAll();
}

