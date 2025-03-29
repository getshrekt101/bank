package com.algomau.bank.exception;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> exception(ApiException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.builder()
                .message(e.getReason())
                .reasonCode(e.getReasonCode())
                .build());
    }
}
