package com.wind.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = { "classpath*:spring/applicationContext-consumer.xml"})
@SpringBootApplication
public class WindAuthWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WindAuthWebApplication.class, args);
	}
}
