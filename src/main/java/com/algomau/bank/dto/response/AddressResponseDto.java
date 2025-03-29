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
public class AddressResponseDto {

    private UUID id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;
    private Type type;

    public enum Type {
        BUSINESS,
        PERSONAL;
    }


}
