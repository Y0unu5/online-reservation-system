package com.cg.ticketbooking.external.client;


import com.cg.ticketbooking.exception.CustomException;
import com.cg.ticketbooking.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping("/doPayment")
    ResponseEntity<Long> doPayment(PaymentRequest paymentRequest);

    default ResponseEntity<Void> fallback(Exception e){
        throw new CustomException("Payment Service is not available...!!","UNAVAILABLE", 500);
    }
}
