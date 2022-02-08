package com.alkemy.ong.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "ORGANIZATIONS")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "IMAGE", nullable = false)
    private String image;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private Integer phone;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "WELCOME_TEXT", nullable = false)
    private String welcomeText;

    @Column(name = "ABOUT_US_TEXT")
    private String aboutUsText;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private Timestamp timeStamp;

    @Column(name = "SOFT_DELETE")
    private boolean softDelete;

    @Column(name = "FACEBOOK_URL")
    private String facebookUrl;

    @Column(name = "LINKEDIN_URL")
    private String linkedinUrl;

    @Column(name = "INSTAGRAM_URL")
    private String instagramUrl;

}