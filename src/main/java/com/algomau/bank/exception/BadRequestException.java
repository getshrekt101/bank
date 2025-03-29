package com.algomau.bank.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;


public class BadRequestException extends ApiException {
    public BadRequestException(String reason, String reasonCode) {
        super(reason, reasonCode, HttpStatus.BAD_REQUEST);
    }
}