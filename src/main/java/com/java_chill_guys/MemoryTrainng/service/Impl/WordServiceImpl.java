package com.java_chill_guys.MemoryTrainng.service.Impl;
import com.java_chill_guys.MemoryTrainng.domain.level.Level;
import com.java_chill_guys.MemoryTrainng.domain.word.Word;
import com.java_chill_guys.MemoryTrainng.repository.WordRepository;
import com.java_chill_guys.MemoryTrainng.service.CryptoService;
import com.java_chill_guys.MemoryTrainng.service.WordService;
import com.java_chill_guys.MemoryTrainng.web.dto.DataDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    private final CryptoService cryptoService;
    private final WordRepository wordRepository;

    public WordServiceImpl(CryptoService cryptoService, WordRepository wordRepository) {
        this.cryptoService = cryptoService;
        this.wordRepository = wordRepository;
    }

    private String getResultString(List<Word> words, String separator) {
        StringBuilder resultString = new StringBuilder();
        for (Word word : words) {
            resultString.append(word.getWord());
            resultString.append(separator);
        }
        return resultString.toString();
    }

    private boolean hardValidate(String words, String userWords) {
        return words.equalsIgnoreCase(userWords);
    }

    private boolean validateSequence(String originalWords, String userWords) {
        List<String> originalList = Arrays.stream(originalWords.split(" ")).map(String::toLowerCase).toList();
        List<String> userList = Arrays.stream(userWords.split(" ")).map(String::toLowerCase).toList();
        return new HashSet<>(originalList).containsAll(userList) && new HashSet<>(userList).containsAll(originalList);
    }

    private int getWordLength(Level level) {
        if (level.getRepeated() >= 9) {
            return 8;
        } else if (level.getRepeated() >= 6) {
            return 7;
        } else if (level.getRepeated() >= 3) {
            return 6;
        }
        return 5;
    }

    private void incrementOrResetLevel(Level level, boolean isCorrect, long nextStage) {
        if (isCorrect) {
            level.setRepeated(level.getRepeated() + 1);
            if (level.getRepeated() == 12) {
                level.setStage(nextStage);
                level.setRepeated(0L);
            }
        }
    }

    @Override
    public DataDto play(DataDto dto) {

        Level level = cryptoService.decrypt(dto.getEncodedLevel());
        boolean isCorrect;
        int length;
        List<Word> words;

        switch (level.getStage().intValue()) {
            case 1 -> {
                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(1, length);

                String necessarySeq = new StringBuilder(dto.getWords()).reverse().toString();
                isCorrect = hardValidate(necessarySeq, dto.getWordsUser());
                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, ""), "", 5);
                }

                incrementOrResetLevel(level, isCorrect, 2L);
                return new DataDto(cryptoService.encrypt(level), getResultString(words, ""), "", 5);
            }
            case 2 -> {
                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(length, length);

                isCorrect = validateSequence(dto.getWords(), dto.getWordsUser());
                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 7);
                }

                incrementOrResetLevel(level, isCorrect, 3L);
                return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 7);
            }
            case 3 -> {
                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(length, length);

                isCorrect = hardValidate(dto.getWords(), dto.getWordsUser());
                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 9);
                }

                incrementOrResetLevel(level, isCorrect, 4L);
                return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 9);
            }
            case 4 -> {
                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(length, length);
                String necessarySeq = new StringBuilder(dto.getWords()).reverse().toString();

                isCorrect = validateSequence(necessarySeq, dto.getWordsUser());
                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 11);
                }

                incrementOrResetLevel(level, isCorrect, 5L);
                Collections.reverse(words);
                return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 11);
            }
            case 5 -> {
                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(length, length);
                String necessarySeq = new StringBuilder(dto.getWords()).reverse().toString();

                isCorrect = hardValidate(necessarySeq, dto.getWordsUser());
                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 13);
                }

                incrementOrResetLevel(level, isCorrect, 0L);
                Collections.reverse(words);
                return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 13);
            }
        }

        return null;
    }
}