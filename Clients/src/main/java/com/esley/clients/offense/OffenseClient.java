package com.esley.clients.offense;

import com.esley.clients.configuration.FeignClientConfiguration;
import com.esley.clients.ticketOffense.TicketOffense;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "offense-service", path = "/offense", configuration = FeignClientConfiguration.class)
public interface OffenseClient {
    @GetMapping("/{offenseId}")
    Offense findOffense(@PathVariable("offenseId") Integer id);
}
