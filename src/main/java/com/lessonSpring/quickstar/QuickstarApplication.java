package com.lessonSpring.quickstar;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class QuickstarApplication {


	public static void main(String[] args) {
		SpringApplication.run(QuickstarApplication.class, args);
	}

}
