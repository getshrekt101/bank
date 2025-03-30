package com.algomau.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequestDto {
    private AddressRequestDto address;
    private String email;
    private String sinNumber;
    private String businessName;
    private Type type;

    public enum Type {
        EMPLOYEE,
        CUSTOMER,
    }
}
