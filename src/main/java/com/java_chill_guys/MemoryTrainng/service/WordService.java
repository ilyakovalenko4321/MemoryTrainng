package com.java_chill_guys.MemoryTrainng.service;

import com.java_chill_guys.MemoryTrainng.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    //Сама логика ОБЯЗАТЕЛЬНО РАСФАСОВАНА по разным методам

}
