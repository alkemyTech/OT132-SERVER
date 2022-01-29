package com.alkemy.ong.common.amazon;

import java.io.InputStream;

public interface IUploadContent {

  String getContentName();

  String getContentType();

  InputStream getInputStream();
}
