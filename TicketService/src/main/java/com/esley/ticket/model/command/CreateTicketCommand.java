package com.esley.ticket.model.command;

import com.esley.ticket.validation.annotation.ExistsEmail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
public class CreateTicketCommand {
    @NotEmpty(message = "DRIVER_EMAIL_NOT_EMPTY")
    @ExistsEmail
    private String driverEmail;
    @PastOrPresent(message = "DATA_IN_FUTURE")
    private LocalDate data;
    @NotEmpty(message = "COUNTRY_NOT_EMPTY")
    private String country;
}
