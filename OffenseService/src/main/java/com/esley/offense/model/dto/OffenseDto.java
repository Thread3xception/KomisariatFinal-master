package com.esley.offense.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode
public class OffenseDto extends RepresentationModel<OffenseDto> {

    private int id;
    private double priceFrom;
    private double priceTo;
    private String description;
    private int scoreFrom;
    private int scoreTo;

}
