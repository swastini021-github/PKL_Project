package com.dimata.logbookmaster.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundExceptionResource extends Exception {

    private static final long serialVersionUID = 1L;

    public NotFoundExceptionResource(String message) {
        super(message);
    }
}