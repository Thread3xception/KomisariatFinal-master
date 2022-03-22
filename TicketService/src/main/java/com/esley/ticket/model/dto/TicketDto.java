package com.esley.ticket.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Set;

@Value
@Builder
@EqualsAndHashCode
public class TicketDto extends RepresentationModel<TicketDto> {

    private int id;
    private int driverId;
    private LocalDate data;
    private double price;
    private int score;
    private boolean status;
    private String country;
    private Set<Integer> ticketOffenseIds;
}
