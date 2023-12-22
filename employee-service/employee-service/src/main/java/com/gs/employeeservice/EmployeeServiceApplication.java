package com.gs.employeeservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
public class EmployeeServiceApplication {

	@Bean(name = "modelMapper")
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

/*
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}*/

	@Bean
	WebClient webClient(){
		return WebClient.builder().build();
	}


	public static void main(String[] args) {

		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
