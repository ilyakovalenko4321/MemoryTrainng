package com.java_chill_guys.MemoryTrainng.service.Props;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.level")
@Data
public class LevelEncryptProps {

    String key;

}
