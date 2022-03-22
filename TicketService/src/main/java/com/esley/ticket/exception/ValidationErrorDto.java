package com.esley.ticket.exception;

import lombok.Value;

@Value
public class ValidationErrorDto {
    String field;
    String code;
}
