package com.alkemy.ong.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "COMMENTS")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "COMMENTS_ID")
  private Long commentsId;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User userId;

  @ManyToOne
  @JoinColumn(name = "NEWS_ID")
  private News news;

  @Column(name = "BODY", nullable = false)
  private String body;


}
