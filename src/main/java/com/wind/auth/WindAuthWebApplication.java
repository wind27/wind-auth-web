package com.wind.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = { "classpath*:spring/applicationContext-consumer.xml"})
@SpringBootApplication
public class WindAuthWebApplication /*extends SpringBootServletInitializer*/ {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(WindAuthWebApplication.class);
//	}

	public static void main(String[] args) {
//		try {
			SpringApplication.run(WindAuthWebApplication.class, args);
//			synchronized (WindAuthWebApplication.class) {
//				while (true) {
//					WindAuthWebApplication.class.wait();
//				}
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("******************************* stop ");
//		}
	}
}
