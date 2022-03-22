package com.esley.ticketoffense.validation.annotation;

import com.esley.ticketoffense.validation.impl.CorrectScoreValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectScoreValidator.class)
public @interface CorrectScore {
    String message() default "SCORE_INCORRECT";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
