package com.alkemy.ong.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguration {

  @Value("${amazonProperties.endpointUrl}")
  private String endpointUrl;

  @Value("${amazonProperties.bucket}")
  private String bucketName;

  @Value("${amazonProperties.accessKey}")
  private String accessKey;

  @Value("${amazonProperties.secretKey}")
  private String secretKey;

  @Value("${amazonProperties.region}")
  private String region;

  public String getBucketName() {
    return this.bucketName;
  }

  @Bean
  public AmazonS3 initializeAmazon() {
    BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

    return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
        .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

  }

}
