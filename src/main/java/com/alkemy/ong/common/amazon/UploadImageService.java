package com.alkemy.ong.common.amazon;

import com.alkemy.ong.config.AwsConfiguration;
import com.alkemy.ong.exception.UploadErrorException;
import com.amazonaws.services.cloudsearchdomain.model.Bucket;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UploadImageService {

  @Autowired
  private AwsConfiguration config;

  public String update(IUploadContent content) {
    try {
      AmazonS3 amazonS3 = config.initializeAmazon();
      String bucketName = config.getBucketName();
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType(content.getContentType());
      amazonS3
          .putObject(new PutObjectRequest(bucketName,
           content.getContentName(),content.getInputStream(), metadata));
      return amazonS3.getUrl(bucketName, content.getContentName()).toString();
    } catch (RuntimeException e) {
      throw new UploadErrorException(e.getMessage());
    }
  }

}
