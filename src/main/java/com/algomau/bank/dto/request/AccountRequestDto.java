package com.algomau.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountRequestDto {
    private Double balance;
    private Type type;
    private BankRequestDto bank;
    private UserRequestDto user;

    public enum Type {
        CHEQUEING ,
        SAVING,
        CREDIT;
    }

}
