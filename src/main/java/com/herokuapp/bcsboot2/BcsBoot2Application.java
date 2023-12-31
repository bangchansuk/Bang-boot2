package com.herokuapp.bcsboot2;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class BcsBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(BcsBoot2Application.class, args);
	}

}
