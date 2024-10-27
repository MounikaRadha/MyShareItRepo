package com.m0wn1ka.MyShareIt.services;

import com.m0wn1ka.MyShareIt.repository.FileUploadsRepository;
import com.m0wn1ka.MyShareIt.repository.RegularWordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class CronHandlingService {
    private final FileUploadsRepository fileUploadsRepository;
    private final RegularWordsRepository regularWordsRepository;
    public String deleteRowsFromFileUploadsTable(){
        fileUploadsRepository.deleteAll();
        log.info("file uploads table deleted");
        return "file uploads table deleted";
    }
    public  String updateRegularWordsTable(){
        regularWordsRepository.updateAllByAlreadyUsedStatusSetToTrue();
        log.info("regular words table deleted");
        return "regular words table updated";
    }
}
