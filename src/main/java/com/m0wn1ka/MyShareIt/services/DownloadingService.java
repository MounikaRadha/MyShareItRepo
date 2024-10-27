package com.m0wn1ka.MyShareIt.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

@Service

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DownloadingService {

   private final S3Service s3Service;
    public URL downloadFile(String regularWord){
        return s3Service.downloadFile(regularWord);
    }

    public String downloadAndSendText(String regularWord) {
     URL url1=  s3Service.downloadFile(regularWord);
        String fileBaseName = regularWord;
        String extension = ".txt";
        try {
            File tempFile = File.createTempFile(fileBaseName, extension);
            FileUtils.copyURLToFile(url1,tempFile);
           return Files.readString(tempFile.toPath());
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return  null;
        }
    }
}
