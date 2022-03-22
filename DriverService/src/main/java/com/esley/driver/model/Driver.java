package com.esley.driver.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"tickets"})
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private int yearsOld;
//    @OneToMany(mappedBy = "driver", cascade = CascadeType.MERGE)
//    private Set<Ticket> tickets;
//
//    public int getTotalScore() {
//        return Optional.ofNullable(tickets)
//                .orElseGet(Collections::emptySet)
//                .stream()
//                .filter(n -> n.checkStatus() == true)
//                .collect(Collectors.summingInt(Ticket::getTotalScore));
//
//    }


}