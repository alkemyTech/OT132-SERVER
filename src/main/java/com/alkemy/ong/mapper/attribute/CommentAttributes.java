package com.alkemy.ong.mapper.attribute;

public enum CommentAttributes {

  USERNAME("username"),
  TIMESTAMP("timestamp"),
  BODY("body");

  private final String fieldName;

  CommentAttributes(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return this.fieldName;
  }
}