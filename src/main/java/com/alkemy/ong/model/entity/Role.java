package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
@NoArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_ROLE")
  private Long Id;

  @Column(name = "NAME", nullable = false)
  private String Name;

  @Column(name = "DESCRIPTION", nullable = true)
  private String Description;

  @CreationTimestamp
  @Column(name = "ROLE_TIMESTAMP")
  private Timestamp TimeStamp;

}
