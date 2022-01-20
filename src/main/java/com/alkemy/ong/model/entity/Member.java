package com.alkemy.ong.model.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "MEMBERS")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Integer memberId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "FACEBOOK_URL")
    private String facebookUrl;

    @Column(name = "INSTAGRAM_URL")
    private String instagramUrl;

    @Column(name = "LINKEDIN_URL")
    private String linkedinUrl;

    @Column(name = "IMAGE", nullable = false)
    private String image;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TIMESTAMP", nullable = false)
    private Timestamp timestamp;

    @Column(name = "SOFT_DELETE")
    private boolean softDelete;

}
