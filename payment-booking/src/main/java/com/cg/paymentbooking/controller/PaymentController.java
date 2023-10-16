package com.cg.paymentbooking.controller;

import com.cg.paymentbooking.model.PaymentRequest;
import com.cg.paymentbooking.model.PaymentResponse;
import com.cg.paymentbooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable String ticketId) {
        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(ticketId),
                HttpStatus.OK
        );
    }

}

