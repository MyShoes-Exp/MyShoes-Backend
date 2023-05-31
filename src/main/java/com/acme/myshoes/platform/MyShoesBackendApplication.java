package com.acme.myshoes.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyShoesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyShoesBackendApplication.class, args);
	}

}
