package com.example.recetarium.demo;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAsync
public class RecetariumBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecetariumBackApplication.class, args);
	}

}
