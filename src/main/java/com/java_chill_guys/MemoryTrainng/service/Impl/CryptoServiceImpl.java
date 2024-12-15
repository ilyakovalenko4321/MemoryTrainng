package com.java_chill_guys.MemoryTrainng.service.Impl;

import com.java_chill_guys.MemoryTrainng.domain.level.Level;
import com.java_chill_guys.MemoryTrainng.service.CryptoService;
import com.java_chill_guys.MemoryTrainng.service.Props.LevelEncryptProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {

    private final LevelEncryptProps props;

    @Override
    public Level decrypt(String encryptedData) {
        String key = props.getKey();

        // Применяем XOR к зашифрованной строке
        String decryptedString = xor(encryptedData, key);

        // Разбираем строку в объект Level
        String[] parts = decryptedString.split(",");
        Long levelId = Long.parseLong(parts[0]);
        Long levelRepeat = Long.parseLong(parts[1]);

        Level level = new Level();
        level.setStage(levelId);
        level.setRepeated(levelRepeat);

        return level;
    }

    @Override
    public String encrypt(Level level) {
        String key = props.getKey();

        // Преобразуем поля Level в строку для шифрования
        String dataToEncrypt = level.getStage() + "," + level.getRepeated();

        // Применяем XOR к строке
        return xor(dataToEncrypt, key);
    }

    private String xor(String input, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            // Применяем XOR побайтово
            result.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }
        return result.toString();
    }
}
