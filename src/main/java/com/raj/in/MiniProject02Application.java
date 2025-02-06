package com.raj.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.raj.in.rest","com.raj.in.service","com.raj.in.runner"})
public class MiniProject02Application {

	public static void main(String[] args) {
		SpringApplication.run(MiniProject02Application.class, args);
	}
}
