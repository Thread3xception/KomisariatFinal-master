package com.esley.driver.exception;

import lombok.Value;

@Value
public class ValidationErrorDto {
    String field;
    String code;
}
