package com.quinbay.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.quinbay.finance.repository")
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

}
