package com.esley.offense.validation.annotation;

import com.esley.offense.validation.impl.PriceRangeValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceRangeValidator.class)
public @interface PriceRange {
    String message() default "PRICE_FROM_OR_PRICE_TO_INVALID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
