package com.blmeena.rosters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RostersApplication {

	public static void main(String[] args) {
		SpringApplication.run(RostersApplication.class, args);
	}

}
