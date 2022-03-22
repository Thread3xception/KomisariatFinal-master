package com.esley.ticket.validation.impl;

import com.esley.ticket.service.FeignService;
import com.esley.ticket.validation.annotation.ExistsEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@RequiredArgsConstructor
public class ExistsEmailValidator implements ConstraintValidator<ExistsEmail, String> {

    private final FeignService feignService;

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return feignService.findOwner(email) != null;
    }
}
