package com.esley.ticket.service;

import com.esley.clients.driver.Driver;
import com.esley.clients.driver.DriverClient;
import com.esley.clients.ticketOffense.TicketOffense;
import com.esley.clients.ticketOffense.TicketOffenseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class FeignService {

    private final DriverClient driverClient;
    private final TicketOffenseClient ticketOffenseClient;

    public Driver findOwner(String email) {
        return driverClient.getSingleDriver(email);
    }

    public Set<TicketOffense> findListTicketOffenseByTicketId(Integer ticketId) {
        return ticketOffenseClient.findAllTicketOffensesForTicketId(ticketId);
    }

}
