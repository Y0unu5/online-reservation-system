package com.cg.ticketbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketId;
    private long trainId;
    private long seats;
    private Instant bookingDate;
    private String bookingStatus;
    private long amount;
}
