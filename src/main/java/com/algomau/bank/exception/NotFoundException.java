package com.algomau.bank.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    public NotFoundException(String reason, String reasonCode) {
        super(reason, reasonCode, HttpStatus.NOT_FOUND);
    }
}