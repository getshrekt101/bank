package com.algomau.bank.controller;

import com.algomau.bank.dto.request.BankRequestDto;
import com.algomau.bank.dto.response.BankResponseDto;
import com.algomau.bank.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BankResponseDto> getBank(@PathVariable UUID id) {
        var response = bankService.getBank(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<BankResponseDto>> getBanks() {
        var response = bankService.getBanks();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BankResponseDto> createBank(@RequestBody BankRequestDto bankRequestDto) {
        var response = bankService.createBank(bankRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BankResponseDto> createBank(@PathVariable UUID id, @RequestBody BankRequestDto bankRequestDto) {
        var response = bankService.updateBank(bankRequestDto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> createBank(@PathVariable UUID id) {
        bankService.deleteBank(id);
        return ResponseEntity.ok().build();
    }
}
