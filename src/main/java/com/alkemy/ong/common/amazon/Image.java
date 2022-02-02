package com.alkemy.ong.common.amazon;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Image implements IImage {

  private String name;
  private String contentType;
  private InputStream inputStream;

  public Image(String file, String name, String contentType) {
    this.name = name;
    this.contentType = contentType;
    this.inputStream = new ByteArrayInputStream(file.getBytes());
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getContentType() {
    return this.getContentType();
  }

  @Override
  public InputStream getInputStream() {
    return inputStream;
  }

}
