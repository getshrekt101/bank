package com.algomau.bank.controller;

import com.algomau.bank.dto.request.AccountRequestDto;
import com.algomau.bank.dto.response.AccountResponseDto;
import com.algomau.bank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable UUID id) {
        AccountResponseDto response = accountService.getAccount(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<AccountResponseDto>> getAccounts() {
        List<AccountResponseDto> response = accountService.getAccounts();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AccountResponseDto> addAccount(@RequestBody AccountRequestDto account) {
        AccountResponseDto response = accountService.addAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AccountResponseDto> updateAccount(@PathVariable UUID id, @RequestBody AccountRequestDto account) {
        AccountResponseDto response = accountService.updateAccount(account, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
}
