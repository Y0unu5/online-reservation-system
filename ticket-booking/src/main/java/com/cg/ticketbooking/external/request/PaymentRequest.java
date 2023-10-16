package com.cg.ticketbooking.external.request;

import com.cg.ticketbooking.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private long bookingId;
    private long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;
}
