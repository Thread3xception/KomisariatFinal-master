package com.esley.ticketoffense.service;

import com.esley.clients.offense.Offense;
import com.esley.clients.offense.OffenseClient;
import com.esley.clients.ticket.Ticket;
import com.esley.clients.ticket.TicketClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeignService {

    private final TicketClient ticketClient;
    private final OffenseClient offenseClient;

    public Ticket findTicketById(Integer id) {
        return ticketClient.findTicket(id);
    }

    public Offense findOffenseById(Integer id) {
        return offenseClient.findOffense(id);
    }

}
