package com.esley.ticketoffense.validation.impl;

import com.esley.clients.offense.Offense;
import com.esley.ticketoffense.model.command.CreateTicketOffenseCommand;
import com.esley.ticketoffense.service.FeignService;
import com.esley.ticketoffense.validation.annotation.CorrectPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@RequiredArgsConstructor
public class CorrectPriceValidator implements ConstraintValidator<CorrectPrice, CreateTicketOffenseCommand> {

    private final FeignService feignService;

    @Override
    public boolean isValid(CreateTicketOffenseCommand command, ConstraintValidatorContext constraintValidatorContext) {
        Offense newOffense = feignService.findOffenseById(command.getOffenseId());
        return newOffense.getPriceFrom() <= command.getPrice() && newOffense.getPriceTo() >= command.getPrice();
    }
}

