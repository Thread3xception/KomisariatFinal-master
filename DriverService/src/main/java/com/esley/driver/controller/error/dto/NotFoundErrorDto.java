package com.esley.driver.controller.error.dto;

import lombok.Value;

@Value
public class NotFoundErrorDto {
    private String type;
    private long id;
}
