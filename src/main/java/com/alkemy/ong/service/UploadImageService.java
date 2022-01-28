package com.alkemy.ong.service;

import com.alkemy.ong.exception.UploadErrorException;
import com.alkemy.ong.service.abstraction.IUploadImageUrl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class UploadImageService implements IUploadImageUrl {

  @Value("${amazonProperties.bucket}")
  private String bucketName;

  @Value("${amazonProperties.endpointURL}")
  private String endpointUrl;

  @Autowired
  private AmazonS3 amazonS3;

  @Override
  public String uploadImage(InputStream input, ObjectMetadata contentType, String fileName) {
    try {
      PutObjectRequest request = new PutObjectRequest(bucketName, fileName, input, contentType);
      amazonS3.putObject(request);
    } catch (RuntimeException e) {
      throw new UploadErrorException(e.getMessage());
    }
    return String.valueOf(amazonS3.getUrl(bucketName, (endpointUrl + fileName)));
  }

}
