package com.algomau.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponseDto {

    private UUID id;
    private BigDecimal amount;
    private String itemName;
    private UUID organizationName;
    private Status status;
    public AccountResponseDto account;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}
