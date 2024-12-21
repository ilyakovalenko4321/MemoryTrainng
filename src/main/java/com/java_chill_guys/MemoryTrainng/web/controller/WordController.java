package com.java_chill_guys.MemoryTrainng.web.controller;

import com.java_chill_guys.MemoryTrainng.domain.level.Level;
import com.java_chill_guys.MemoryTrainng.service.CryptoService;
import com.java_chill_guys.MemoryTrainng.service.WordService;
import com.java_chill_guys.MemoryTrainng.web.dto.DataDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "memorytrainng-production.up.railway.app/") //TODO: Починить
public class WordController {

    private final WordService wordService;
    private final CryptoService cryptoService;

    public WordController(WordService wordService, CryptoService cryptoService) {
        this.wordService = wordService;
        this.cryptoService = cryptoService;
    }

    @PostMapping("/play")
    public Map<String, Object> play(@RequestBody(required = false) DataDto dto) {

        if (dto == null || dto.getEncodedLevel().isEmpty() || dto.getEncodedLevel().isBlank()) {
            dto = new DataDto();
            Level blankLevel = new Level(1L, 0L);
            dto.setEncodedLevel(cryptoService.encrypt(blankLevel));
            dto.setWords("");
            dto.setWordsUser("");
        }
        // Получаем результат из сервиса
        DataDto responseData = wordService.play(dto);

        Map<String, Object> result = new HashMap<>();
        result.put("DataDto", responseData);

        return result;
    }


}
