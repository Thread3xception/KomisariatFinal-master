package com.esley.ticketoffense.validation.annotation;

import com.esley.ticketoffense.validation.impl.CorrectPriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectPriceValidator.class)
public @interface CorrectPrice {
    String message() default "PRICE_INCORRECT";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}