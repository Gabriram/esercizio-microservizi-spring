package com.corso.bike;

import org.springframework.boot.SpringApplication;

public class TestBikeApplication {

	public static void main(String[] args) {
		SpringApplication.from(BikeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
