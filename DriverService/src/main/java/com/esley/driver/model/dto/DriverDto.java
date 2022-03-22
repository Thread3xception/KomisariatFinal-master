package com.esley.driver.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode
public class DriverDto extends RepresentationModel<DriverDto> {
    private int id;
    private String email;
    private String name;
    private String surname;
    private int yearsOld;
    private int score;
    private Set<Integer> ticketIds;
}
