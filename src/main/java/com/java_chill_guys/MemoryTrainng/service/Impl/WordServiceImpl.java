package com.java_chill_guys.MemoryTrainng.service.Impl;

import com.java_chill_guys.MemoryTrainng.MemoryTrainngApplication;
import com.java_chill_guys.MemoryTrainng.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    public int firstLevel() {
        return 0;
    }
    public int secondLevel() {
        return 0;
    }
    public int thirdLevel() {
        return 0;
    }
    public int fourthLevel() {
        return 0;
    }
    public int fifthLevel() {
        return 0;
    }
    @Override
    public int play() {

        firstLevel();
        secondLevel();
        thirdLevel();
        fourthLevel();
        fifthLevel();
        return 0;
    }

}
