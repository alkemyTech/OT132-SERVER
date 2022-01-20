package com.alkemy.ong.model.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
public class Category {

    @Column(name = "CATEGORY_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String image;

    private Timestamp timestamp;

    private boolean softDelete;

}
