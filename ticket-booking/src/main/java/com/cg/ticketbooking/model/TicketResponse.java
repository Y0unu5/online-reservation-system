package com.cg.ticketbooking.model;

import lombok.*;

import java.time.Instant;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private long bookingId;
    private long amount;
    private Instant bookingDate;
    private String bookingStatus;
    private TrainDetails trainDetails;
    private PaymentDetails paymentDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TrainDetails{
        private String trainName;
        private long noOfSeats;
        private long trainId;
        private String startStation;
        private String endStation;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentDetails{
        private long paymentId;
        private PaymentMode paymentMode;
        private Instant paymentDate;
        private String paymentStatus;
    }

}
