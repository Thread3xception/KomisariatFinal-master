package com.esley.clients.ticket;

import com.esley.clients.driver.Driver;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Ticket {
    private int id;
    private Driver driver;
    private LocalDate data;
    private String country;
}
