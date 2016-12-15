package com.iezview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class IezSwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(IezSwayApplication.class, args);
	}
}
