package com.algomau.bank.dto.response;

import com.algomau.bank.dto.request.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAccountResponseDto {
    private UUID id;
    private Role role;
    private String userName;
    public UserRequestDto user;
    public enum Role {
        ROLE_USER, ROLE_ADMIN, ROLE_TELLER;
    }
}
