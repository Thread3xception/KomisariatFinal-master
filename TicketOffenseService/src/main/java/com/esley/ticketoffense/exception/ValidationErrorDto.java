package com.esley.ticketoffense.exception;

import lombok.Value;

@Value
public class ValidationErrorDto {
    String field;
    String code;
}
