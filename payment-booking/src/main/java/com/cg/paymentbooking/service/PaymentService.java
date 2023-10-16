package com.cg.paymentbooking.service;

import com.cg.paymentbooking.model.PaymentRequest;
import com.cg.paymentbooking.model.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(String  orderId);
}
