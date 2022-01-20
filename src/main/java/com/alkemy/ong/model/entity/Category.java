package com.alkemy.ong.model.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Column(name = "CATEGORY_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "SOFT_DELETE")
    private boolean softDelete;

}
