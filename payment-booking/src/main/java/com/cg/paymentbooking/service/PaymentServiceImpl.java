package com.cg.paymentbooking.service;

import com.cg.paymentbooking.entity.Payment;
import com.cg.paymentbooking.model.PaymentMode;
import com.cg.paymentbooking.model.PaymentRequest;
import com.cg.paymentbooking.model.PaymentResponse;
import com.cg.paymentbooking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private final PaymentRepository paymentRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}", paymentRequest);
        Payment payment= Payment.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .amount(paymentRequest.getAmount())
                .bookingId(paymentRequest.getBookingId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .build();
        paymentRepository.save(payment);

        log.info("Transaction Completed with Id: {}", payment.getPaymentId());

        return payment.getPaymentId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String bookingId) {
        log.info("Getting payment details for the Order Id: {}", bookingId);

        Payment payment = paymentRepository.findByBookingId(Long.valueOf(bookingId));

        PaymentResponse paymentResponse= PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .paymentDate(payment.getPaymentDate())
                .paymentMode(PaymentMode.valueOf(payment.getPaymentMode()))
                .amount(payment.getAmount())
                .status(payment.getPaymentStatus())
                .bookingId(payment.getBookingId())
                .build();

        return paymentResponse;
    }
}
