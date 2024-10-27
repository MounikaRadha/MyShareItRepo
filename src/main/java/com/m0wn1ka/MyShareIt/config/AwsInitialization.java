package com.m0wn1ka.MyShareIt.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AwsInitialization {

    public AmazonS3 amazonS3;
    @Autowired
    public AWSS3Config awsS3Config;

    @PostConstruct
    public void init() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
                awsS3Config.getAwsAccessKey(),
                awsS3Config.getAwsSecretKey()
        );
        if (this.amazonS3 == null) {
           System.out.println("Initializing AWSInitialization...");
            this.amazonS3 = AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration(
                                    awsS3Config.getAwsEndPoint(), awsS3Config.getAwsRegion()))
                    .withPathStyleAccessEnabled(true)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
            System.out.println("Initialized AWSInitialization");
        } else {
            System.out.println("AwsS3StorageService already initialized");
        }
    }
}