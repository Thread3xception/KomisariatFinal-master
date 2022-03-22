package com.esley.offense.exception;

import lombok.Value;

@Value
public class ValidationErrorDto {
    String field;
    String code;
}
