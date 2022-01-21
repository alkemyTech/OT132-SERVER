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
@Table(name = "CONTACTS")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CONTACT_ID", nullable = false)
  private Long contactId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "PHONE", nullable = false)
  private Integer phone;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "MESSAGE", nullable = false)
  private String message;

  @Column(name = "DELETED_AT")
  private Timestamp deletedAt;
}