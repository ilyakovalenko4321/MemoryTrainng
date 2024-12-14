package com.java_chill_guys.MemoryTrainng.web.controller;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "null")
////////////////// REMOVE NULL ORIGIN!
public class WordController {

    @GetMapping("/test_method")
    //public String testMethod(){return "index.html";}
    public Map<String, Object> getTestData() {
        // Create sample data to return as JSON
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from the Spring Boot server!");
        response.put("timestamp", LocalDateTime.now().toString());
        return response; // Automatically converted to JSON
    }

}
