package com.alkemy.ong.model.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NEWS")
@Getter
@Setter
public class News {

    @Column(name = "NEWS_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long newsId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "IMAGE", nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category; 

    @Column(name = "SOFT_DELETE")
    private boolean softDelete;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private Timestamp timestamp;
}
