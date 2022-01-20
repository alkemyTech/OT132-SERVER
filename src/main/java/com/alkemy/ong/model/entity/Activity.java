
package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


	@Column(name = "NAMES", nullable = false)
	private String name;

	@Column(name = "CONTENTS", nullable = false)
	private String content;

	@Column(name = "IMAGES", nullable = false)
	private String image;

	@Column(name = "TIMESTAMPS")
	private Timestamp timestap;		

	@Column(name = "SOFT_DELETES")
	private Boolean softDelete;

}
