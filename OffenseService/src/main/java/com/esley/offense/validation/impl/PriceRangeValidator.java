package com.esley.offense.validation.impl;

import com.esley.offense.model.command.CreateOffenseCommand;
import com.esley.offense.validation.annotation.PriceRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@RequiredArgsConstructor
public class PriceRangeValidator implements ConstraintValidator<PriceRange, CreateOffenseCommand> {

    @Override
    public boolean isValid(CreateOffenseCommand createOffenseCommand, ConstraintValidatorContext constraintValidatorContext) {
        return createOffenseCommand.getPriceFrom() < createOffenseCommand.getPriceTo();
    }
}
