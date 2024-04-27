package com.example.HWSpring2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HwSpring2Application {
	public static void main(String[] args) {
		SpringApplication.run(HwSpring2Application.class, args);
	}

}
