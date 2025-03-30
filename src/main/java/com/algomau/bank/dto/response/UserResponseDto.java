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
public class UserResponseDto {
    private UUID id;
    private AddressResponseDto address;
    private String email;
    private String sinNumber;
    private String businessName;
    private Type type;

    public enum Type {
        EMPLOYEE,
        CUSTOMER,
    }
}
