package com.algomau.bank.controller;

import com.algomau.bank.dto.request.AccountRequestDto;
import com.algomau.bank.dto.response.AccountResponseDto;
import com.algomau.bank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable UUID id) {
        AccountResponseDto response = accountService.getAccount(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAccounts() {
        List<AccountResponseDto> response = accountService.getAccounts();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> addAccount(@RequestBody AccountRequestDto account) {
        AccountResponseDto response = accountService.addAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDto> updateAccount(@PathVariable UUID id, @RequestBody AccountRequestDto account) {
        AccountResponseDto response = accountService.updateAccount(account, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
}
