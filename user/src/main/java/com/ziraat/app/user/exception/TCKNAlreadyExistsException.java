package com.ziraat.app.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TCKNAlreadyExistsException extends RuntimeException {
    public TCKNAlreadyExistsException(String s) {
        super(s);
    }
}
