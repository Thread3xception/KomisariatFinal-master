package com.esley.ticket.exception;

import lombok.Value;

@Value
public class EntityNotFoundException extends RuntimeException {

    private Class type;
    private long id;


}
