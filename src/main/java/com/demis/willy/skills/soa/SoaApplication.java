package com.demis.willy.skills.soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.demis.willy.skills.soa.repository")
public class SoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoaApplication.class, args);
	}

}
