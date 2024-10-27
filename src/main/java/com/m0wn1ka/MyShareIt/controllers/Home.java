package com.m0wn1ka.MyShareIt.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Home {
    @RequestMapping("/welcome")
    public String welcome() {
        return """
                Welcome to My Share It!
                This is a underDevelopment project.
                Can be used to share images and text seamlessly
                """;
    }
    @RequestMapping("/")
    public String home() {
        return "welcome to the home page";
    }
}
