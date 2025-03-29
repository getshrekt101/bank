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
public class UserRequestDto {
    private AddressRequestDto address;
    private String email;
    private String sinNumber;
    private String businessName;
    private Type type;

    public enum Type {
        BUSINESS,
        PERSONAL;
    }
}
