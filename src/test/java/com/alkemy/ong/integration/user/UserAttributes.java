package com.alkemy.ong.integration.user;

public enum UserAttributes {

  NULL_FIRST_NAME("firstName"),
  NULL_LAST_NAME("lastName"),
  NULL_EMAIL("email"),
  NULL_PASSWORD("password"),
  NULL_PHOTO("photo");

  private final String fieldName;

  UserAttributes(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return this.fieldName;
  }

}
