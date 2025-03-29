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
public class UserAccountResponseDto {
    private UUID id;
    private Role role;
    private String userName;
    private String password;
    public UserResponseDto user;
    public enum Role {
        USER, ADMIN;
    }
}
