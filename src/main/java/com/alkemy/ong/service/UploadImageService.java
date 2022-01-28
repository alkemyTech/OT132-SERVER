package com.alkemy.ong.service;

import com.alkemy.ong.config.AwsConfiguration;
import com.alkemy.ong.exception.UploadErrorException;
import com.alkemy.ong.service.abstraction.IUploadImageUrl;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UploadImageService implements IUploadImageUrl {

  @Value("${amazonProperties.bucket}")
  private String bucketName;

  @Value("${amazonProperties.endpointUrl}")
  private String endpointUrl;

  @Autowired
  private AwsConfiguration config;

  @Override
  public String uploadImage(InputStream input, ObjectMetadata contentType, String fileName) {
    try {
      PutObjectRequest request = new PutObjectRequest(bucketName, fileName, input, contentType);
      AmazonS3 amazonS3 = config.initializeAmazon();
      amazonS3.putObject(request);
      return String.valueOf(amazonS3.getUrl(bucketName, fileName));
    } catch (RuntimeException e) {
      throw new UploadErrorException(e.getMessage());
    }
  }

}
