package com.algomau.bank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> exception(ApiException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.builder()
                .message(e.getReason())
                .reasonCode(e.getReasonCode())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e) {
        return ResponseEntity.status(500).body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .reasonCode("server-error")
                .build());
    }
}
