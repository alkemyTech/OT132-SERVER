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
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Table(name = "TESTIMONIAL")
public class Testimonial {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE", nullable = true)
  private String image;

  @Column(name = "CONTENT", nullable = true)
  private String content;

  @Column(name = "TIMESTAMP")
  @CreationTimestamp
  private Timestamp timestamp;

  @Column(name = "SOFT_DELETE")
  private Boolean softDelete = Boolean.FALSE;
}
