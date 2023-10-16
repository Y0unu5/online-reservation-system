package com.cg.paymentbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentBookingApplication.class, args);
	}

}
