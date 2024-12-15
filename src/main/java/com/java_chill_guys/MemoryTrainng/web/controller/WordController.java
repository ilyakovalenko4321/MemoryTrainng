package com.java_chill_guys.MemoryTrainng.web.controller;

import com.java_chill_guys.MemoryTrainng.service.WordService;
import com.java_chill_guys.MemoryTrainng.web.dto.DataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @GetMapping("/play")
    public String play(@RequestBody DataDto dto){



    }

}
