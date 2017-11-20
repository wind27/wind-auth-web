package com.wind.auth;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.wind.auth.interceptor.SecurityInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@ImportResource(locations = { "classpath*:spring/applicationContext-consumer.xml"})
@Import({DispatcherServletAutoConfiguration.class, EmbeddedServletContainerAutoConfiguration.class,
		ErrorMvcAutoConfiguration.class, HttpEncodingAutoConfiguration.class,
		HttpMessageConvertersAutoConfiguration.class, JacksonAutoConfiguration.class, MultipartAutoConfiguration.class,
		ServerPropertiesAutoConfiguration.class, WebMvcAutoConfiguration.class})

@SpringBootApplication
@DubboComponentScan(basePackages = "com.wind.auth.controller")
public class WindAuthWebApplication /*extends SpringBootServletInitializer*/ {
	public static void main(String[] args) {
			SpringApplication.run(WindAuthWebApplication.class, args);
	}
}
