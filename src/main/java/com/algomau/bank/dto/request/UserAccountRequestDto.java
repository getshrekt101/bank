package com.algomau.bank.dto.request;

import com.algomau.bank.dto.response.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAccountRequestDto {
    private Role role;
    private String userName;
    private String password;
    public UserRequestDto user;
    public enum Role {
        USER, ADMIN;
    }
}
