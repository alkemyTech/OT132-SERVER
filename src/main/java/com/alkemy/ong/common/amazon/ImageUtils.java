package com.alkemy.ong.common.amazon;

import com.alkemy.ong.config.AwsConfiguration;
import com.alkemy.ong.exception.ExternalServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageUtils {

  @Autowired
  private AwsConfiguration config;

  public String upload(IImage image) throws ExternalServiceException {
    try {
      AmazonS3 amazonS3 = config.initializeAmazon();
      String bucketName = config.getBucketName();
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(image.getName().getBytes().length);
      metadata.setContentType(image.getContentType());
      amazonS3.putObject(
          new PutObjectRequest(bucketName,
              image.getName(),
              image.getInputStream(),
              metadata));
      return amazonS3.getUrl(bucketName, image.getName()).toString();
    } catch (RuntimeException e) {
      throw new ExternalServiceException(e.getMessage());
    }
  }

}
