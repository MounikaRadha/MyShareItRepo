package com.m0wn1ka.MyShareIt.config;


import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Getter
@Configuration
@Data
public class AWSS3Config {
    public String getAwsEndPoint() {
        return awsEndPoint;
    }



    public String getAwsRegion() {
        return awsRegion;
    }



    public String getAwsBucketName() {
        return awsBucketName;
    }



    public String getAwsAccessKey() {
        return awsAccessKey;
    }



    public String getAwsSecretKey() {
        return awsSecretKey;
    }


    @Value("${aws.serviceEndpoint}")
    private String awsEndPoint;

    @Value("${aws.signingRegion}")
    private String awsRegion;

    @Value("${aws.bucketName}")
    private String awsBucketName;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

}
