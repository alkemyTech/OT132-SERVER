package com.alkemy.ong.common;

public enum RoleEnum {
	ADMIN, USER;

	public String getRole() {

		switch (this) {
			case ADMIN:
				return "ADMIN";
			default:
				return "USER";
		}
	}
}
