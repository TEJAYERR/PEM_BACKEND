package com.personal_expenses_management.PEM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class PemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PemApplication.class, args);
	}

}
