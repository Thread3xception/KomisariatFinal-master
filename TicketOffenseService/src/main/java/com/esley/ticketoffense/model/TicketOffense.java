package com.esley.ticketoffense.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class TicketOffense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "ticket_id", nullable = false)  // t1 o1    t1 o2   t1  o3
    private int ticketId;
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "offense_id", nullable = false)
    private int offenseId;
    private double price;
    private int score;
    private String country;

    public TicketOffense(int ticketId, int offenseId, double price, int score, String country) {
        this.ticketId = ticketId;
        this.offenseId = offenseId;
        this.price = price;
        this.score = score;
        this.country = country;

//        ticket.getTicketOffenses().add(this);
//        offense.getTicketOffenses().add(this);

    }
}
