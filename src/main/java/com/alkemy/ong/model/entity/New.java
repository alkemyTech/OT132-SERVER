package com.alkemy.ong.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news")
@Getter
@Setter
public class New {

    @Column(name = "NEW_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "IMAGE", nullable = false)
    private String image;

    @Column(name = "CATEGORY_ID",nullable = false)
    private long categoryId;

    @Column(name = "SOFT_DELETE")
    private boolean softDelete;

    @Column(name = "NEW_TIMESTAMP")
    @CreationTimestamp
    private Timestamp timestamp;
}
