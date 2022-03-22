package com.esley.driver.exception;

import lombok.Value;

@Value
public class EntityNotFoundException extends RuntimeException {

    private Class type;
    private long id;


}
