package com.m0wn1ka.MyShareIt.controllers;

import com.m0wn1ka.MyShareIt.services.DownloadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;

@RestController
@RequestMapping("/download")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DownloadingController {
   private final DownloadingService downloadingService;
    @RequestMapping("/file")
    public URL downloadFile(@RequestPart("regularWord") String regularWord){
        return  downloadingService.downloadFile(regularWord);
    }
    @RequestMapping("/text")
    public String downloadText(@RequestPart("regularWord") String regularWord){
//        return regularWord;
        return  downloadingService.downloadAndSendText(regularWord);
    }

}
