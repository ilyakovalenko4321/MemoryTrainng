package com.java_chill_guys.MemoryTrainng.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class WordController {

    @GetMapping("/test_method")
    public String testMethod(){
        return "index.html";
    }

}
