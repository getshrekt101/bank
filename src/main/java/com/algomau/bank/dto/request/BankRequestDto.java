package com.algomau.bank.dto.request;

import com.algomau.bank.dto.response.AddressResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BankRequestDto {
    private String name;
    private String code;
    private String branchNumber;
    private Type type;
    private UUID accountNumber;
    private AddressRequestDto address;

    public enum Type {
        COMMERICAL ,
        CENTRAL
    }
}
