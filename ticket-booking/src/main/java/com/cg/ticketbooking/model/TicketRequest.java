package com.cg.ticketbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {
    private long trainId;
    private long noOfSeats;
    private long amount;
    private PaymentMode paymentMode;
}
