package com.cg.ticketbooking.controlller;

import com.cg.ticketbooking.model.TicketRequest;
import com.cg.ticketbooking.model.TicketResponse;
import com.cg.ticketbooking.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")

public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/bookTicket")
    public ResponseEntity<Long> bookTicket(@RequestBody TicketRequest ticketRequest) {
        long ticketId = ticketService.bookTicket(ticketRequest);
        return new ResponseEntity<>(ticketId, HttpStatus.CREATED);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponse> getTicketDetails(@PathVariable long ticketId) {
        TicketResponse ticketResponse
                = ticketService.getTicketDetails(ticketId);

        return new ResponseEntity<>(ticketResponse,
                HttpStatus.OK);
    }

}
