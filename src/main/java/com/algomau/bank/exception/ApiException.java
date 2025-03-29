package com.algomau.bank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {

    private final String reasonCode;
    private final String reason;
    private final HttpStatus httpStatus;

    protected ApiException( String reasonCode, String reason, HttpStatus httpStatus) {
        super(reason);
        this.reason = reason;
        this.reasonCode = reasonCode;
        this.httpStatus = httpStatus;
    }
}