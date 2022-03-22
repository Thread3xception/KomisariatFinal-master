package com.esley.ticket.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"ticketOffenses"})
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "driver_id", nullable = false)
    private int driverId;
    private LocalDate data;
    private String country;
    //@OneToMany(mappedBy = "ticket", cascade = CascadeType.MERGE)
    //@JoinTable(name = "Ticket_Offence", joinColumns = {@JoinColumn(name="ticket_id")}, inverseJoinColumns = {@JoinColumn(name="offensie_id")})
    //private Set<TicketOffense> ticketOffenses;


    public Ticket(int driverId, LocalDate data, String country) {
        this.driverId = driverId;
        this.data = data;
        this.country = country;
    }


    public boolean checkStatus() {
        long diff = ChronoUnit.DAYS.between(data, LocalDate.now());
        return diff < 365;
    }

}
