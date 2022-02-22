package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "NEWS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {

  @Id
  @Column(name = "NEWS_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long newsId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "TEXT", nullable = false)
  private String text;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "CATEGORY_ID")
  private Category category;

  @Column(name = "SOFT_DELETE")
  private boolean softDelete;

  @Column(name = "TIMESTAMP")
  @CreationTimestamp
  private Timestamp timestamp;

}
