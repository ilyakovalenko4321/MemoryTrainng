package com.java_chill_guys.MemoryTrainng.service;

import com.java_chill_guys.MemoryTrainng.domain.level.Level;

public interface CryptoService {

    Level decrypt(String encryptedLevel);

    String encrypt(Level level);

}
