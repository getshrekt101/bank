package com.algomau.bank.dto.request;

import com.algomau.bank.dto.response.AccountResponseDto;
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
public class TransactionRequestDto {
    private BigDecimal amount;
    private String itemName;
    private UUID organizationName;
    private Status status;
    public AccountRequestDto account;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}
