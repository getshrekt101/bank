package com.algomau.bank.controller;

import com.algomau.bank.dto.request.TransactionRequestDto;
import com.algomau.bank.dto.response.TransactionResponseDto;
import com.algomau.bank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionResponseDto> getTransaction(@PathVariable UUID id) {
        TransactionResponseDto response = transactionService.getTransaction(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<TransactionResponseDto>> getTransactions() {
        List<TransactionResponseDto> response = transactionService.getTransactions();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto response = transactionService.createTransaction(transactionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TransactionResponseDto> updateTransaction(@PathVariable UUID id, @RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto response = transactionService.updateTransaction(transactionRequestDto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }

}
