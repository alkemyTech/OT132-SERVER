package com.alkemy.ong.service.abstraction;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface IUploadImageUrl {

  String uploadImage(InputStream input, ObjectMetadata contentType, String fileName);
}
