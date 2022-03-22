package com.esley.clients.ticketOffense;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketOffense {
    private int id;
    private int ticketId;
    private int offenseId;
    private double price;
    private int score;
    private String country;
}
