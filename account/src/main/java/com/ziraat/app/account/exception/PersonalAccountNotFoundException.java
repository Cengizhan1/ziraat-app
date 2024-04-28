package com.ziraat.app.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonalAccountNotFoundException extends RuntimeException {
    public PersonalAccountNotFoundException(String message) {
        super(message);
    }
}
