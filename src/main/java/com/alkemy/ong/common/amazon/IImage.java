package com.alkemy.ong.common.amazon;

import java.io.InputStream;

public interface IImage {

  String getName();

  String getContentType();

  InputStream getInputStream();

}
