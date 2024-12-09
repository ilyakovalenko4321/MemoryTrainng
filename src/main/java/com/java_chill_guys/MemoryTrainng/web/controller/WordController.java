package com.java_chill_guys.MemoryTrainng.web.controller;

import com.java_chill_guys.MemoryTrainng.service.WordService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WordController {

    private final WordService wordService;

    //Для принятия запроса с user части

}
