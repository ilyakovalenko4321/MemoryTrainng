package com.java_chill_guys.MemoryTrainng.web.controller;

import com.java_chill_guys.MemoryTrainng.domain.level.Level;
import com.java_chill_guys.MemoryTrainng.service.CryptoService;
import com.java_chill_guys.MemoryTrainng.service.WordService;
import com.java_chill_guys.MemoryTrainng.web.dto.DataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;
    private final CryptoService cryptoService;

    @GetMapping("/play")
    public DataDto play(@RequestBody DataDto dto){
        if(dto.getEncodedLevel().isEmpty()){
            Long stage = 1L;
            Long repeated = 0L;
            Level blankLevel = new Level(stage, repeated);
            String d = cryptoService.encrypt(blankLevel);
            dto.setEncodedLevel(cryptoService.encrypt(blankLevel));
        }

        return wordService.play(dto);

    }

}
