package com.alkemy.ong.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SLIDES")
@Getter
@Setter
@NoArgsConstructor
public class Slide {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SLIDE_ID", nullable = false)
  private Long slideId;

  @Column(name = "IMAGE_URL", nullable = false)
  private String imageUrl;

  @Column(name = "TEXT")
  private String text;

  @Column(name = "SLIDE_ORDER", nullable = false)
  private Integer order;

}
