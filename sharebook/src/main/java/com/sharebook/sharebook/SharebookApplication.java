package com.sharebook.sharebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.sharebook.sharebook.controller"})
public class SharebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharebookApplication.class, args);
	}

}
