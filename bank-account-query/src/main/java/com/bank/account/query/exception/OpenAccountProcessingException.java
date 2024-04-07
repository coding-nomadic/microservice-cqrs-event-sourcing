package com.bank.account.query.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OpenAccountProcessingException extends RuntimeException {

    public OpenAccountProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenAccountProcessingException(String message, String errorCode) {
        super(message);
    }
}