package com.algomau.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAccountRequestDto {
    private Role role;
    private String username;
    private String password;
    public UserRequestDto user;
    public enum Role {
        ROLE_USER, ROLE_ADMIN, ROLE_TELLER;
    }
}
