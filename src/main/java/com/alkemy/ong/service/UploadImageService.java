package com.alkemy.ong.service;

import java.io.InputStream;

import com.alkemy.ong.service.abstraction.IUploadImageUrl;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class UploadImageService implements IUploadImageUrl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadImageService.class);

    @Value("${amazonProperties.bucket}")
    private String bucketName;

    @Value("${amazonProperties.endpointURL}")
    private String endpointURL;

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public String uploadImage(InputStream input, ObjectMetadata contentType, String fileName) {
        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, input, contentType);
            LOGGER.info("Subiendo archivo con el nombre..." + fileName);
            amazonS3.putObject(request);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return String.valueOf(amazonS3.getUrl(bucketName, (endpointURL + fileName)));
    }

}
