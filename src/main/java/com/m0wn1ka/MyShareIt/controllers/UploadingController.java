package com.m0wn1ka.MyShareIt.controllers;

import com.m0wn1ka.MyShareIt.services.UploadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadingController {

    private final UploadingService uploadingService;

    @PostMapping("/file")
    public String uploadFile(@RequestPart("file1") MultipartFile file){

        return uploadingService.uploadFile(file);
    }

    @PostMapping("/text")
    public  String uploadText(@RequestPart("text1") String text){
        return uploadingService.uploadtext(text);
    }
}
