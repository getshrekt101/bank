package com.algomau.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountResponseDto {

    private UUID id;
    private BigDecimal balance;
    private Type type;
    private BankResponseDto bank;
    private UserResponseDto user;
    private UUID accountNumber;
    private List<TransactionResponseDto> transactions;

    public enum Type {
        CHEQUEING ,
        SAVING,
        CREDIT;
    }

}
