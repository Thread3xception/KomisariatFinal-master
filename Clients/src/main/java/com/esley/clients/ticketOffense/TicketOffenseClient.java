package com.esley.clients.ticketOffense;

import com.esley.clients.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(value = "ticketOffense-service", path = "/ticket_offense", configuration = FeignClientConfiguration.class)
public interface TicketOffenseClient {
    @GetMapping("/list_offense_id/{offenseId}")
    Set<TicketOffense> findAllTicketOffensesForOffenseId(@PathVariable("offenseId") Integer offenseId);

    @GetMapping("/list_ticket_id/{ticketId}")
    Set<TicketOffense> findAllTicketOffensesForTicketId(@PathVariable("ticketId") Integer ticketId);
}
