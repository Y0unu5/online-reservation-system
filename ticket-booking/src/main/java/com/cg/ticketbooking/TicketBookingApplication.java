package com.cg.ticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class TicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public Docket swaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/tickets/**"))
				.apis(RequestHandlerSelectors.basePackage("com.cg.ticketbooking"))
				.build()
				.apiInfo(apiCustomData());
	}

	private ApiInfo apiCustomData(){
		return new ApiInfo(
				"Ticket Booking API Application",
				"Ticket Booking API Documentation",
				"1.0",
				"@NoLicense",
				new Contact(
						"Mahmad Yunus",
						"http://no-url.com",
						"mky@hotmail.com"
						),
				"http://no-url.com",
				"Ticket Booking $Terms",
				Collections.emptyList()
		);
	}
}
