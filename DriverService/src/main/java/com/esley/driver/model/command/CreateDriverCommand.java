package com.esley.driver.model.command;

import com.esley.driver.validation.annotation.UniqueEmail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@EqualsAndHashCode
public class CreateDriverCommand {
    @NotEmpty(message = "EMAIL_NOT_EMPTY")
    @UniqueEmail
    private String email;
    @NotEmpty(message = "NAME_NOT_EMPTY")
    private String name;
    @NotEmpty(message = "SURNAME_NOT_EMPTY")
    private String surname;
    @Positive(message = "YEARS_OLD_NOT_NEGATIVE")
    private int yearsOld;
    @PositiveOrZero(message = "SCORE_OF_DRIVER_NOT_NEGATIVE")
    private int score;
}
