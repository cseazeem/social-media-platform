package com.azeem.socialmedia;

import com.azeem.socialmedia.utils.IdGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public IdGenerator idGenerator() {
		return new IdGenerator(1L, 1L);
	}
}
