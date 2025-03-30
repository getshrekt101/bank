package com.algomau.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BankRequestDto {
    private String name;
    private String code;
    private String branchNumber;
    private Type type;
    private AddressRequestDto address;

    public enum Type {
        COMMERICAL ,
        CENTRAL
    }
}
