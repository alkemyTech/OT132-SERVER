package com.alkemy.ong.common.amazon;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface IUploadContent {

  String getContentName();

  String getContentType();

  InputStream getInputStream();
}
