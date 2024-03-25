package com.example.marathon.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    @PreAuthorize("")
    public String test(){
        System.out.println(":)");
        return "test";
    }
}
