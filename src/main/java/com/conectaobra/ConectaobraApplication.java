package com.conectaobra.conectaobra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConectaobraApplication{

	public static void main(String[] args) {
		SpringApplication.run(ConectaobraApplication.class, args);
	}

	@Bean
	public String apiName(){
		return "ConectaObra";
	}

}
