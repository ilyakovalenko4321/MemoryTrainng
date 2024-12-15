package com.java_chill_guys.MemoryTrainng.service.Impl;

import com.java_chill_guys.MemoryTrainng.domain.exceptions.WrongAnswer;
import com.java_chill_guys.MemoryTrainng.domain.level.Level;
import com.java_chill_guys.MemoryTrainng.domain.word.Word;
import com.java_chill_guys.MemoryTrainng.repository.WordRepository;
import com.java_chill_guys.MemoryTrainng.service.CryptoService;
import com.java_chill_guys.MemoryTrainng.service.WordService;
import com.java_chill_guys.MemoryTrainng.web.dto.DataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final CryptoService cryptoService;
    private final WordRepository wordRepository;

    private String getResultString(List<Word> words, String separator){
        StringBuilder resultString = new StringBuilder();
        for(Word word: words){
            resultString.append(word.getWord());
            resultString.append(separator);
        }
        return resultString.toString();
    }

    private boolean hardValidate(String words, String userWords){
        return words.equals(userWords);
    }

    @Override
    public DataDto play(DataDto dto) {

        Level level = cryptoService.decrypt(dto.getEncodedLevel());

        switch (level.getStage().intValue()) {
            case 1 -> {
                if(hardValidate(dto.getWords(), dto.getWordsUser())) {
                    int length = 5;
                    if (level.getRepeated() >= 9) {
                        length = 8;
                    } else if (level.getRepeated() >= 6) {
                        length = 7;
                    } else if (level.getRepeated() >= 3) {
                        length = 6;
                    }
                    List<Word> words = wordRepository.selectNumOfWords(1, length);

                    level.setRepeated(level.getRepeated() + 1);

                    if (level.getRepeated() == 12) {
                        level.setStage(1L);
                        level.setRepeated(0L);
                    }
                    return new DataDto(cryptoService.encrypt(level), getResultString(words, ""), "");
                }
                else{
                    throw new WrongAnswer("Неправильный ввод");
                }
            }
            case 2 -> {



            }
            case 3 -> {
            }
            case 4 -> {
            }
            case 5 -> {
            }
        }

        return null;
    }


}
