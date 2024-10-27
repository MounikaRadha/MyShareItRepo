package com.m0wn1ka.MyShareIt.services;

import com.m0wn1ka.MyShareIt.Enums.UploadType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UploadingService {

  private final  S3Service s3Service;

    public String uploadFile(MultipartFile file) {
        System.out.println("in uploadFileService");
         return     s3Service.uploadFileTypeToS3(file);

    }

    public String uploadtext(String text) {
        String fileBaseName=text.substring(0,5);
        try {
            File tempFile = File.createTempFile(fileBaseName, ".txt");
          Path path1= Path.of(tempFile.getPath());
            Files.writeString(path1, text,
                    StandardCharsets.UTF_8);
            return s3Service.uploadTextTypeToS3(tempFile);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            log.info("uploadingService.java:))");
            return null;
        }
    }
}
