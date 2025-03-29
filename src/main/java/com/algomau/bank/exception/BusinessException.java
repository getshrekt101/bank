package com.algomau.bank.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends ApiException {

    public BusinessException(String reason, String reasonCode) {
        super(reason, reasonCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}