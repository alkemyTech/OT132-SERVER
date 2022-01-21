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
@Table(name = "ROLES")
@Getter
@Setter
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROLE_ID", nullable = false)
  private Long roleId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "DESCRIPTION", nullable = true)
  private String description;

  @CreationTimestamp
  @Column(name = "TIMESTAMP")
  private Timestamp timestamp;

}
