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
@Table(name = "Slides")
@Getter
@Setter
@NoArgsConstructor
public class Slide {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_SLIDE")
  private Long ID_SLIDE;

  @Column(name = "IMAGE_URL", nullable = false)
  private String IMAGE_URL;

  @Column(name = "TEXT")
  private String TEXT;

  @Column(name = "SLIDE_ORDER", nullable = false)
  private Integer SLIDE_ORDER;
}
