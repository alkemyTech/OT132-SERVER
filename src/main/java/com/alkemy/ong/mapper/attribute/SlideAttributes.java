package com.alkemy.ong.mapper.attribute;

public enum SlideAttributes {

  SLIDE_ID("slideId"),
  IMAGE_URL("imageUrl"),
  TEXT("text"),
  ORDER("order");

  private final String fieldName;

  SlideAttributes(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return this.fieldName;
  }

}
