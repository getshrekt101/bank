package com.algomau.bank.dto.request;

import com.algomau.bank.dto.response.BankResponseDto;
import com.algomau.bank.dto.response.TransactionResponseDto;
import com.algomau.bank.dto.response.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountRequestDto {
    private Double balance;
    private Type type;
    private BankRequestDto bank;
    private UserRequestDto user;
    private UUID accountNumber;
    private List<TransactionResponseDto> transactions;

    public enum Type {
        CHEQUEING ,
        SAVING,
        CREDIT;
    }

}
