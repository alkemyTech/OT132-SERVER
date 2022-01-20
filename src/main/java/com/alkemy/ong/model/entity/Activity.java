
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACTIVITIES")
public class Activity {
  @Id
	@Column(name = "ACTIVITY_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long activityId;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "IMAGE", nullable = false)
	private String image;

	@Column(name = "TIMESTAMP")
	@CreationTimestamp
	private Timestamp timestamp;		

	@Column(name = "SOFT_DELETE")
	private boolean softDelete;

}
