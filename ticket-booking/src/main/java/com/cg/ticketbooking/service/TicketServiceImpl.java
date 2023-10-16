package com.cg.ticketbooking.service;

import com.cg.ticketbooking.entity.Ticket;
import com.cg.ticketbooking.exception.CustomException;
import com.cg.ticketbooking.external.client.PaymentService;
import com.cg.ticketbooking.external.client.TrainService;
import com.cg.ticketbooking.external.request.PaymentRequest;
import com.cg.ticketbooking.external.response.PaymentResponse;
import com.cg.ticketbooking.model.TicketRequest;
import com.cg.ticketbooking.model.TicketResponse;
import com.cg.ticketbooking.repository.TicketRepository;
import com.cg.traindetails.entity.Train;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;

@Service
@Log4j2
public class TicketServiceImpl implements TicketService{

    @Autowired
    private  TicketRepository ticketRepository;

    @Autowired
    private  PaymentService paymentService;

    @Autowired
    private  TrainService trainService;

    @Autowired
    private  RestTemplate restTemplate;
    @Override
    public long bookTicket(TicketRequest ticketRequest) {

        log.info("Placing Ticket Request: {}", ticketRequest);

        trainService.reduceQuantity(ticketRequest.getTrainId(), ticketRequest.getNoOfSeats());

        Ticket ticket
                = Ticket.builder()
                .amount(ticketRequest.getAmount())
                .bookingStatus("BOOKED")
                .trainId(ticketRequest.getTrainId())
                .bookingDate(Instant.now())
                .seats(ticketRequest.getNoOfSeats())
                .build();
        ticket=ticketRepository.save(ticket);
        log.info("Calling Payment Service to complete the payment");

        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .bookingId(ticket.getTicketId())
                .paymentMode(ticketRequest.getPaymentMode())
                .amount(ticketRequest.getAmount())
                .build();

        String bookingStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done Successfully. Changing the Booking status to PLACED");
            bookingStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error occurred in payment. Changing booking status to PAYMENT_FAILED");
            bookingStatus = "PAYMENT_FAILED";
        }

        ticket.setBookingStatus(bookingStatus);
        ticketRepository.save(ticket);
        return ticket.getTicketId();
    }

    @Override
    public TicketResponse getTicketDetails(long ticketId) {
        log.info("Get order details for Order Id : {}", ticketId);
        Ticket ticket
                = ticketRepository.findById(ticketId)
                .orElseThrow(()->new CustomException("Ticket not found for the Ticket Id:" + ticketId,
                        "NOT_FOUND",
                        404)
                );
        log.info("Invoking Train service to fetch the train for id: {}", ticket.getTrainId());
         Train trainResponse
                = restTemplate.getForObject(
                       "http://TRAIN-SERVICE/trains/" + ticket.getTrainId(), Train.class
        );

        log.info("Getting payment information form the payment Service");
        PaymentResponse paymentResponse
                = restTemplate.getForObject(
                "http://PAYMENT-SERVICE/payment/ticket/"  + ticket.getTicketId(), PaymentResponse.class
        );
        TicketResponse.TrainDetails trainDetails
                = TicketResponse.TrainDetails.builder()
                .trainName(trainResponse.getTrainName())
                .startStation(trainResponse.getStartStation())
                .endStation(trainResponse.getEndStation())
                .noOfSeats(trainResponse.getTotalSeats())
                .trainId(trainResponse.getTrainId())
                .build();

        TicketResponse.PaymentDetails paymentDetails
                = TicketResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentStatus(paymentResponse.getStatus())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        TicketResponse ticketResponse
                = TicketResponse
                .builder()
                .bookingId(ticket.getTicketId())
                .bookingStatus(ticket.getBookingStatus())
                .amount(ticket.getAmount())
                .bookingDate(ticket.getBookingDate())
                .trainDetails(trainDetails)
                .paymentDetails(paymentDetails)
                .build();
        return ticketResponse;
    }
}
