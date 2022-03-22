package com.esley.driver.service;

import com.esley.clients.ticket.Ticket;
import com.esley.clients.ticket.TicketClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class FeignService {

    private final TicketClient ticketClient;

    public Ticket findTicketById(Integer id) {
        return ticketClient.findTicket(id);
    }

    public Set<Ticket> findTicketListForDriver(Integer id) {
        return ticketClient.findTicketListByDriverId(id);
    }
}
