package com.rasysbox.country;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceCountryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCountryApplication.class, args);
	}

}
