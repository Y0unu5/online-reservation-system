package com.example.gatewayroutes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack(){
        return "Payment Service is down..... Please Try again after some time!!";
    }

    @GetMapping("/ticketServiceFallBack")
    public String ticketServiceFallBack(){
        return "Ticket Service is down..... Please Try again after some time!!";
    }

    @GetMapping("/trainServiceFallBack")
    public String trainServiceFallBack(){
        return "Train Service is down..... Please Try again after some time!!";
    }

}
