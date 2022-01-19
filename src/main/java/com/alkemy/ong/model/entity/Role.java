package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_ROLE")
  private Long ID_ROLE;

  @Column(name = "NAME", nullable = false)
  private String NAME;

  @Column(name = "DESCRIPTION", nullable = true)
  private String DESCRIPTION;

  @Column(name = "ROLE_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp ROLE_TIMESTAMP;

}
