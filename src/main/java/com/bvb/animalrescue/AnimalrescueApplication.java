package com.bvb.animalrescue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimalrescueApplication {
	
//	@Bean
//	WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/*").allowedOrigins("http://localhost:5173");
//			}
//		};
//	}
	public static void main(String[] args) {
		SpringApplication.run(AnimalrescueApplication.class, args);
	}

}
