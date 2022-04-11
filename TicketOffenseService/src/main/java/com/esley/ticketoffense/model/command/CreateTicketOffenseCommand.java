package com.esley.ticketoffense.model.command;

import com.esley.ticketoffense.validation.annotation.CorrectPrice;
import com.esley.ticketoffense.validation.annotation.CorrectScore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.PositiveOrZero;

@Data
@EqualsAndHashCode
@CorrectPrice
@CorrectScore
public class CreateTicketOffenseCommand {
    @PositiveOrZero(message = "TICKET_ID_POSITIVE_OR_ZERO")
    private int ticketId;
    @PositiveOrZero(message = "OFFENSE_ID_POSITIVE_OR_ZERO")
    private int offenseId;
    @PositiveOrZero(message = "PRICE_POSITIVE_OR_ZERO")
    private double price;
    @PositiveOrZero(message = "SCORE_POSITIVE_OR_ZERO")
    private int score;
}
