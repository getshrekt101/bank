package com.algomau.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionRequestDto {
    private Double amount;
    private String itemName;
    private String organizationName;
    private Status status;
    private UUID accountId;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}
