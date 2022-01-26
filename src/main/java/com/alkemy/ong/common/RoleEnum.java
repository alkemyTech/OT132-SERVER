package com.alkemy.ong.common;

public enum RoleEnum {
  ADMIN("ADMIN"),
  USER("USER");
	
	private String role;
	
	private RoleEnum(String role){
		this.role = role;
	}
	
	public String getRole(){
		
		return this.role;
	}
}
