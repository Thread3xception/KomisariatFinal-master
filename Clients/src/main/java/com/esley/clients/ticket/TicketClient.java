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
    @PreAuthorize("hasRole('ROLE_POLICEMAN')")
    Set<Ticket> findTicketListByDriverId(@PathVariable("driverId") Integer driverId);
}
