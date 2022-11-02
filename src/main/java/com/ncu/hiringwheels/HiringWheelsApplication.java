package com.ncu.hiringwheels;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ncu.hiringwheels.services.InitService;

@SpringBootApplication
public class HiringWheelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiringWheelsApplication.class, args);
	}
	@Bean
	CommandLineRunner init (InitService initService){
		return args -> {
//			initService.start();
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
