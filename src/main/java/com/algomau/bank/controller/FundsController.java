package com.algomau.bank.controller;

import com.algomau.bank.dto.request.TransactionRequestDto;
import com.algomau.bank.dto.response.TransactionResponseDto;
import com.algomau.bank.service.FundsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/funds")
public class FundsController {

    private final FundsService fundsService;

    public FundsController(FundsService fundsService) {
        this.fundsService = fundsService;
    }

    @PostMapping("/deposit")
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionResponseDto> depositFunds(@RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto response = fundsService.depositFunds(transactionRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{fromId}/transfer/{toId}")
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionResponseDto> transferFunds(
            @PathVariable UUID fromId,
            @PathVariable UUID toId,
            @RequestBody TransactionRequestDto transactionRequestDto) {

        TransactionResponseDto response = fundsService.transferFunds(transactionRequestDto, fromId, toId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionResponseDto> withdrawFunds(@RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto response = fundsService.withdrawFunds(transactionRequestDto);
        return ResponseEntity.ok(response);
    }
}
