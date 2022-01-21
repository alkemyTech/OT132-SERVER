/*
COMO desarrollador QUIERO agregar la entidad UserPARA representar en la implementación la estructura de datos

Criterios de aceptación: Nombre de tabla: users. Los campos son:
firstName: VARCHAR NOT NULL
lastName: VARCHAR NOT NULL
email: VARCHAR UNIQUE NOT NULL
password: VARCHAR NOT NULL
photo: VARCHAR NULLABLE
roleId: Clave foranea hacia ID de Role
timestamps y 
softDelete: boolean.
 */

package com.alkemy.ong.model.entity;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;		


@Entity 
@Table (name = "USERS")
public class User {

	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column( name = "ID", nullable = false, unique = true)
private Long userId;

@Column( name = "FIRST_NAME", nullable = false)
private String firstName;

@Column( name = "LAST_NAME", nullable = false)
private String lastName;

@Column( name = "EMAIL", nullable = false, unique = true)
private String email;

@Column( name = "LAST_NAME", nullable = false)
private String password;

@Column( name = "PHOTO", nullable = true)
private String photo;

@Column( name = "ROLE_ID", nullable = false)
private String roleId;

@Column( name = "TIMESTAMPS", nullable = false)
private Timestamp timestamps;

@Column(name = "SOFT_DELETE")
private boolean softDelete;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the timestamps
	 */
	public Timestamp getTimestamps() {
		return timestamps;
	}

	/**
	 * @param timestamps the timestamps to set
	 */
	public void setTimestamp(Timestamp timestamps) {
		this.timestamps = timestamps;
	}

	/**
	 * @return the softDelete
	 */
	public boolean isSoftDelete() {
		return softDelete;
	}

	/**
	 * @param softDelete the softDelete to set
	 */
	public void setSoftDelete(boolean softDelete) {
		this.softDelete = softDelete;
	}

	@Override
	public String toString() {
		return "User{" + "userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", photo=" + photo + ", roleId=" + roleId + ", timestamps=" + timestamps + ", softDelete=" + softDelete + '}';
	}




		

}
