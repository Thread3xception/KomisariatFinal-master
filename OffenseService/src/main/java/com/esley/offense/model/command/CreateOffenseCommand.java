package com.esley.offense.model.command;

import com.esley.offense.validation.annotation.PriceRange;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@EqualsAndHashCode
@PriceRange
public class CreateOffenseCommand {
    @Positive(message = "PRICE_FROM_NOT_NEGATIVE")
    private double priceFrom;
    @Positive(message = "PRICE_TO_NOT_NEGATIVE")
    private double priceTo;
    @NotEmpty(message = "DESCRIPTION_NOT_EMPTY")
    private String description;
    @Positive(message = "SCORE_FROM_NOT_NEGATIVE")
    private int scoreFrom;
    @Positive(message = "SCORE_TO_NOT_NEGATIVE")
    private int scoreTo;
}
