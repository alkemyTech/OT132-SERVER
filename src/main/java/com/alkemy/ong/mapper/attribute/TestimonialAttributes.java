package com.alkemy.ong.mapper.attribute;

public enum TestimonialAttributes {

  ID("testimonialId"),
  NAME("name"),
  IMAGE("image"),
  CONTENT("content"),
  TIMESTAMP("timestamp"),
  SOFT_DELETE("softDelete");

  private final String fieldName;

  TestimonialAttributes(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return this.fieldName;
  }

}
