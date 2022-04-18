package com.revature;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CashOverflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashOverflowApplication.class, args);
	}
	
	/**
	 * Spring requires this Bean to run
	 * 
	 * @author Cameron, Amir, Chandra
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
