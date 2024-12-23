package com.java_chill_guys.MemoryTrainng.service.Impl;
import com.java_chill_guys.MemoryTrainng.domain.level.Level;
import com.java_chill_guys.MemoryTrainng.domain.word.Word;
import com.java_chill_guys.MemoryTrainng.repository.WordRepository;
import com.java_chill_guys.MemoryTrainng.service.CryptoService;
import com.java_chill_guys.MemoryTrainng.service.WordService;
import com.java_chill_guys.MemoryTrainng.web.dto.DataDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        if (level.getRepeated() > 9) {
            return 8;
        } else if (level.getRepeated() > 6) {
            return 7;
        } else if (level.getRepeated() > 3) {
            return 6;
        }
        return 5;
    }

    private void incrementOrResetLevel(Level level, boolean isCorrect, long nextStage) {
        if (isCorrect) {
            level.setRepeated(level.getRepeated() + 1);
            if (level.getRepeated() > 13) {
                level.setStage(nextStage);
                level.setRepeated(0L);
            }
        } else {
            if (level.getRepeated() > 9) {
                level.setRepeated(10L);
            } else if (level.getRepeated() > 6) {
                level.setRepeated(7L);
            } else if (level.getRepeated() > 3) {
                level.setRepeated(4L);
            } else if (level.getRepeated() > 0) {
                level.setRepeated(1L);
            }
        }
    }

    @Override
    public DataDto play(DataDto dto) {

        Level level = cryptoService.decrypt(dto.getEncodedLevel());
        boolean isCorrect;
        int length;
        List<Word> words = List.of();

        switch (level.getStage().intValue()) {
            case 1 -> {
                String necessarySeq = new StringBuilder(dto.getWords()).reverse().toString();
                isCorrect = hardValidate(necessarySeq, dto.getWordsUser());
                incrementOrResetLevel(level, isCorrect, 2L);

                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(1, length);

                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, ""), "", 5, "1й этап: На данном этапе вам надо ввести его \"перевернутым\"  (столб - блотс)",
                            "Неверно! Попробуйте еще раз.");
                }

                return new DataDto(cryptoService.encrypt(level), getResultString(words, ""), "", 5, "1й этап: На данном этапе вам надо ввести его \"перевернутым\"  (столб - блотс)","Верно! Вы правильно запомнили текст.");
            }
            case 2 -> {
                isCorrect = validateSequence(dto.getWords(), dto.getWordsUser());
                incrementOrResetLevel(level, isCorrect, 3L);

                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(length, length);
                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 17, "2й этап: На данном этапе вам надо ввести слова в любом порядке",
                            "Неверно! Попробуйте еще раз.");
                }

                return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 17, "2й этап: На данном этапе вам надо ввести слова в любом порядке", "Верно! Вы правильно запомнили текст.");
            }
            case 3 -> {
                isCorrect = hardValidate(dto.getWords(), dto.getWordsUser());
                incrementOrResetLevel(level, isCorrect, 4L);

                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(length, length);

                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 19, "3й этап: На данном этапе вам надо ввести слова в правильном порядке",
                            "Неверно! Попробуйте еще раз.");
                }

                return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 19, "3й этап: На данном этапе вам надо ввести слова в правильном порядке", "Верно! Вы правильно запомнили текст.");
            }
            case 4 -> {
                String necessarySeq = new StringBuilder(dto.getWords()).reverse().toString();
                isCorrect = validateSequence(necessarySeq, dto.getWordsUser());
                incrementOrResetLevel(level, isCorrect, 5L);

                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(length, length);

                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 21, "4й этап: На данном этапе вам надо ввести слова перевернутыми в любом порядке",
                            "Неверно! Попробуйте еще раз.");
                }

                //Collections.reverse(words);
                return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 21, "4й этап: На данном этапе вам надо ввести слова перевернутыми в любом порядке", "Верно! Вы правильно запомнили текст.");
            }
            case 5 -> {
                String necessarySeq = new StringBuilder(dto.getWords()).reverse().toString();
                isCorrect = hardValidate(necessarySeq, dto.getWordsUser());
                incrementOrResetLevel(level, isCorrect, 0L);

                length = getWordLength(level);
                words = wordRepository.selectNumOfWords(length, length);

                if (!isCorrect) {
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 23, "5й этап: На данном этапе вам надо ввести слова в перевернутыми в правильном порядке",
                            "Неверно! Попробуйте еще раз.");
                }

                //Collections.reverse(words);
                return new DataDto(cryptoService.encrypt(level), getResultString(words, " "), "", 23, "5й этап: На данном этапе вам надо ввести слова в перевернутыми в правильном порядке", "Верно! Вы правильно запомнили текст.");
            }
        }

        return null;
    }
}
