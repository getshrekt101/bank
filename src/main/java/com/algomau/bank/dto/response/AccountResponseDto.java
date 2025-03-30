package com.algomau.bank.dto.response;

import com.algomau.bank.dto.request.AccountRequestDto;
import com.algomau.bank.dto.request.UserRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class AccountResponseDto {

    private UUID id;
    private Double balance;
    private AccountRequestDto.Type type;
    private BankResponseDto bank;
    private UserRequestDto user;
    @JsonBackReference
    private List<TransactionResponseDto> transactions;

    public enum Type {
        CHEQUEING ,
        SAVING,
        CREDIT;
    }

}
