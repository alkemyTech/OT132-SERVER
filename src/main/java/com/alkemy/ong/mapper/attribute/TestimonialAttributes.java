package com.alkemy.ong.mapper.attribute;

public enum TestimonialAttributes {

  NAME("name"),
  IMAGE("image"),
  CONTENT("content"),
  TIMESTAMP("timestamp"),
  SOFTDELETE("SOFT_DELETE");

  private final String fieldName;

  TestimonialAttributes(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return this.fieldName;
  }
}
