package com.algomau.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BankResponseDto {

    private UUID id;
    private String name;
    private String code;
    private String branchNumber;
    private Type type;
    private UUID accountNumber;
    private AddressResponseDto address;

    public enum Type {
        COMMERICAL ,
        CENTRAL
    }
}
