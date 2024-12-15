package com.java_chill_guys.MemoryTrainng.web.controller;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

import com.java_chill_guys.MemoryTrainng.service.Impl.WordServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "null")
////////////////// REMOVE NULL ORIGIN!
public class WordController {
    String userTask = "TEXT";
    String userLevel = "1";
    //userTask = WordServiceImpl.userTaskOutput()

    @GetMapping("/sendUserTask")
    public Map<String, Object> sendUserTask() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", userTask);
        response.put("level", userLevel);
        return response; // Automatically converted to JSON
    }

    @PostMapping("/verifyUserData")
    public Map<String, Object> verifyUserData(@RequestBody Map<String, String> payload) {
        String userInput = payload.get("input"); // Extract user input from the request
        boolean isCorrect = userTask.equalsIgnoreCase(userInput); // Compare input with task text

        Map<String, Object> response = new HashMap<>();
        response.put("correct", isCorrect); // Send result
        return response;
    }

}
