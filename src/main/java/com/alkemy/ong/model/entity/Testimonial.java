package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Testimonials")
public class Testimonial {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE")
  private String image;

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "TESTIMONIAL_TIMESTAMP")
  private Timestamp timestamp;

  @Column(name = "TESTIMONIAL_DELETE")
  private Boolean softDelete = Boolean.FALSE;
}
