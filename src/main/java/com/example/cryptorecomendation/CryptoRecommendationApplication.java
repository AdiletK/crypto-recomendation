package com.example.cryptorecomendation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

@SpringBootApplication
public class CryptoRecommendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoRecommendationApplication.class, args);
	}

}
