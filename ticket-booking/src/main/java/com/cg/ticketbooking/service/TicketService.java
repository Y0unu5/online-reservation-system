package com.cg.ticketbooking.service;

import com.cg.ticketbooking.model.TicketRequest;
import com.cg.ticketbooking.model.TicketResponse;

public interface TicketService {

    long bookTicket(TicketRequest ticketRequest);


    TicketResponse getTicketDetails(long ticketId);
}
