package com.restorent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RestorentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestorentManagementApplication.class, args);
	}

}
