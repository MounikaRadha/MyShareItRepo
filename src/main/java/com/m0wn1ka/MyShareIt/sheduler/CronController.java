package com.m0wn1ka.MyShareIt.sheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CronController {
    private final CleanUpCron cleanUpCron;
    @RequestMapping("/cron")
    public String callCronTab(){
        cleanUpCron.cleanUp();
        return "cron finished";
    }
}
