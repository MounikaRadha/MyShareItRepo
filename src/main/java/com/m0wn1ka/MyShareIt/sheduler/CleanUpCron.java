package com.m0wn1ka.MyShareIt.sheduler;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.m0wn1ka.MyShareIt.config.AWSS3Config;
import com.m0wn1ka.MyShareIt.config.AwsInitialization;
import com.m0wn1ka.MyShareIt.services.CronHandlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CleanUpCron {
    private  final AwsInitialization awsInitialization;
    private final AWSS3Config awsConfig;
    private final CronHandlingService cronHandlingService;
    @Scheduled(cron = "0 0 0 * * *")
    public void cleanUp() {
        String  bucketName=awsConfig.getAwsBucketName();
        List<S3ObjectSummary> s3ObjectSummaries = this.awsInitialization.amazonS3.listObjects(bucketName).getObjectSummaries();
        for (S3ObjectSummary file : s3ObjectSummaries){
            this.awsInitialization.amazonS3.deleteObject(bucketName, file.getKey());
        }
        cronHandlingService.deleteRowsFromFileUploadsTable();
        cronHandlingService.updateRegularWordsTable();

    }
}
