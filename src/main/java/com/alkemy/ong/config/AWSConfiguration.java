package com.alkemy.ong.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

    @Value("$ {aws_endpoint_URL}")
    private String endpointURL;

    @Value("$ {aws_bucket}")
    private String bucketName;

    @Value("$ {aws_access_key}")
    private String accessKey;

    @Value("$ {aws_secret_key}")
    private String secretKey;

    @Value("$ {aws_region}")
    private String region;


    public String getBucketName() {
        return this.bucketName;
    }

    @Bean
    public AmazonS3 initializeAmazon() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        
        return AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

    }



}
