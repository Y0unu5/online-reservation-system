package com.cg.ticketbooking.external.client;

import com.cg.ticketbooking.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "TRAIN-SERVICE/trains")
public interface TrainService {

    @PutMapping("/reduceSeats/{trainId}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable long trainId,
            @RequestParam long seats
    );
    default ResponseEntity<Void> fallback(Exception e){
        throw new CustomException("Train Service is not available...!!","UNAVAILABLE", 500);
    }
}
