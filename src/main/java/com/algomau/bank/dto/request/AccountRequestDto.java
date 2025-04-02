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
public class AccountRequestDto {
    private Double balance;
    private Type type;
    private UUID bankId;
    private UUID userId;

    public enum Type {
        CHEQUEING ,
        SAVING,
        CREDIT;
    }

}
