package com.alkemy.ong.common.amazon;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;

public class Image implements IImage {

  private final String name;
  private final String contentType;
  private final InputStream inputStream;

  public Image(String fileEncode64, String name, String contentType) {
    this.name = name;
    this.contentType = contentType;
    this.inputStream = new ByteArrayInputStream(Base64.decodeBase64(fileEncode64));
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getContentType() {
    return this.contentType;
  }

  @Override
  public InputStream getInputStream() {
    return inputStream;
  }

}
