package com.vilka.app.vendor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class VendorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorApplication.class, args);
	}

	@Bean
	public CommandLineRunner testEnv(@Value("${JWT_SECRET:NOT_FOUND}") String secret) {
		return args -> System.out.println("JWT_SECRET = " + secret);
	}
}
