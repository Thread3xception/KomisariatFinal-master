package com.esley.driver.controller.error.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDto {
    private String field;
    private String code;
}
