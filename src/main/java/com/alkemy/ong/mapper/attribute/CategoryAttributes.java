package com.alkemy.ong.mapper.attribute;

public enum CategoryAttributes {

  CATEGORY_ID("categoryId"),
  NAME("name"),
  DESCRIPTION("description"),
  IMAGE("image"),
  TIMESTAMP("timestamp"),
  SOFT_DELETE("softDelete");

  private final String fieldName;

  CategoryAttributes(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return this.fieldName;
  }
}