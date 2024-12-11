package com.java_chill_guys.MemoryTrainng;

import com.java_chill_guys.MemoryTrainng.service.Impl.WordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MemoryTrainngApplication implements CommandLineRunner {

	private final WordServiceImpl wordServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(MemoryTrainngApplication.class, args);
	}


	@Override
	public void run(String... args) {
		// Основная логика консольного приложения
		System.out.println("Приложение запущено!");
		// Например, вызов сервисов или обработка данных
		while(true) {
			int result_code = wordServiceImpl.play();
			if (result_code == -1) {
				break;
			}
		}
	}

}
