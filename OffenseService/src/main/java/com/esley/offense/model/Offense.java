package com.esley.offense.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "ticketOffenses")
@Entity
public class Offense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double priceFrom;
    private double priceTo;
    private String description;
    private int scoreFrom;
    private int scoreTo;

}
