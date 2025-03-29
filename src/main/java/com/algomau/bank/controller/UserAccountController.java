package com.algomau.bank.controller;

import com.algomau.bank.dto.request.UserAccountRequestDto;
import com.algomau.bank.dto.response.UserAccountResponseDto;
import com.algomau.bank.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/useraccounts")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountResponseDto> getUserAccount(@PathVariable UUID id) {
        UserAccountResponseDto response = userAccountService.getUserAccount(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserAccountResponseDto>> getUserAccounts() {
        List<UserAccountResponseDto> response = userAccountService.getUserAccounts();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserAccountResponseDto> createUserAccount(@RequestBody UserAccountRequestDto userAccount) {
        UserAccountResponseDto response = userAccountService.createUserAccount(userAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccountResponseDto> updateUserAccount(@PathVariable UUID id, @RequestBody UserAccountRequestDto userAccount) {
        UserAccountResponseDto response = userAccountService.updateUserAccount(userAccount, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable UUID id) {
        userAccountService.deleteUserAccount(id);
        return ResponseEntity.ok().build();
    }
}
