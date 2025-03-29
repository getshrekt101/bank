package com.algomau.bank.exception;


import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String reason, String reasonCode) {
        super(reason, reasonCode, HttpStatus.UNAUTHORIZED);
    }
}