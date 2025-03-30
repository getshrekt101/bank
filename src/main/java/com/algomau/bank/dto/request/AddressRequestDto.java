package com.algomau.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressRequestDto {
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
