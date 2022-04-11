package com.esley.clients.ticket;

import com.esley.clients.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@FeignClient(value = "ticket-service", path = "/ticket", configuration = FeignClientConfiguration.class)
public interface TicketClient {
    @GetMapping("/{id}")
    Ticket findTicket(@PathVariable("id") Integer id);

    @GetMapping("/list/{driverId}")
    Set<Ticket> findTicketListByDriverId(@PathVariable("driverId") Integer driverId);

    @GetMapping("/score/{ticketId}")
    int getSumOfScoreTicketById(@PathVariable("ticketId") Integer ticketId);
}
