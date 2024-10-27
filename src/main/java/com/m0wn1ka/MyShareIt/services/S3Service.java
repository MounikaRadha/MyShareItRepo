package com.m0wn1ka.MyShareIt.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.m0wn1ka.MyShareIt.Enums.UploadType;
import com.m0wn1ka.MyShareIt.config.AWSS3Config;
import com.m0wn1ka.MyShareIt.config.AwsInitialization;
import com.m0wn1ka.MyShareIt.models.FileUploads;
import com.m0wn1ka.MyShareIt.models.RegularWords;
import com.m0wn1ka.MyShareIt.repository.FileUploadsRepository;
import com.m0wn1ka.MyShareIt.repository.RegularWordsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class S3Service {

   private final RegularWordsRepository regularWordsRepository;

  private  final  FileUploadsRepository fileUploadsRepository;

  private  final AwsInitialization awsInitialization;

   private final AWSS3Config awsS3Config;
   public ArrayList<String> handleUpload(UploadType uploadType){
       String uniqueID = UUID.randomUUID().toString();
       RegularWords regularWord1= regularWordsRepository.findFirstByAlreadyUsedStatusFalse();
       regularWord1.alreadyUsedStatus=true;
       regularWordsRepository.save(regularWord1);
       FileUploads fileUploads1 = new FileUploads();
       fileUploads1.uuid=uniqueID;
       fileUploads1.dataType=uploadType.toString();
       fileUploads1.genericName=regularWord1.regularWord;
       fileUploads1.s3Path="need to change";//may be can be removed no use..
       fileUploadsRepository.save(fileUploads1);
       ArrayList<String> list = new ArrayList<>();
        list.add(uniqueID);
        list.add(regularWord1.regularWord);
        return list;
   }
    public String uploadFileTypeToS3( MultipartFile file){
      ArrayList<String> response= handleUpload(UploadType.FileUpload);
      File file1=copyUploadToTempFile(file);
      addFile(file1,response.get(0));
//      generatePreSignedURL(response.get(0));
      return response.get(1);
    }
    private File copyUploadToTempFile(MultipartFile uploadedFile)  {
       try {
           System.out.println("copy uplaod to temp file ....");
           String fileName = uploadedFile.getOriginalFilename();
           String fileBaseName = FilenameUtils.getBaseName(fileName);
           String extension = FilenameUtils.getExtension(fileName);
           File tempFile = File.createTempFile(fileBaseName, extension);
           uploadedFile.transferTo(tempFile.toPath());
           uploadedFile.transferTo(tempFile);
           return tempFile;
       }
       catch (IOException e) {
           e.printStackTrace();
           return null;
       }
    }
    public void addFile(File fileToAdd,String uploadPath) {
        //add file at helix s3 bucket
        System.out.println("add file s3service ....");
        uploadPath="/uploads/"+uploadPath;
        this.awsInitialization.amazonS3.putObject(getRootDirectory(),  uploadPath, fileToAdd);
    }
    protected String getRootDirectory() {
        //gives helix s3 bucket
        return awsS3Config.getAwsBucketName();
    }
    public URL generatePreSignedURL(String filePath) {
        String rootDirectory=awsS3Config.getAwsBucketName();
        System.out.println("Generating pre-signed URL for: " + rootDirectory + "/" + filePath);
        Calendar cal = Calendar.getInstance();
        Integer expiryTimeInMinutes=3*60;
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        HttpMethod accessMethod = HttpMethod.GET;
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(rootDirectory, filePath)
                .withExpiration(cal.getTime())
                .withMethod(accessMethod);
        ResponseHeaderOverrides headers = new ResponseHeaderOverrides();
        request.setResponseHeaders(headers);
        URL presignedURL = this.awsInitialization.amazonS3.generatePresignedUrl(request);
        System.out.println("Aws S3 Pre-signed URL: " + presignedURL);
        return presignedURL;
    }
    public URL downloadFile(String regularWord){
      FileUploads fileUpload1=fileUploadsRepository.findByGenericName(regularWord);
      String uuid=fileUpload1.uuid;
      uuid="/uploads/"+uuid;
      return  generatePreSignedURL(uuid);
    }

    public String uploadTextTypeToS3(File file) {
        ArrayList<String> response= handleUpload(UploadType.TextUpload);
        addFile(file,response.get(0));
//        generatePreSignedURL(response.get(0));
        return response.get(1);

    }
}
