package com.esley.ticketoffense.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode
public class TicketOffenseDto {

    private int id;
    private int ticketId;
    private int offenseId;
    private double price;
    private int score;
}
