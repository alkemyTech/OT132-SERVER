package com.alkemy.ong.service.abstraction;

import java.io.InputStream;

import com.amazonaws.services.s3.model.ObjectMetadata;

public interface IUploadImageUrl {

    String uploadImage(InputStream input, ObjectMetadata contentType, String fileName);
}
