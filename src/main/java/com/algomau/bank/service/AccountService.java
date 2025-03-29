package com.algomau.bank.service;

import com.algomau.bank.domain.Account;
import com.algomau.bank.domain.repository.AccountRepository;
import com.algomau.bank.dto.request.AccountRequestDto;
import com.algomau.bank.dto.response.AccountResponseDto;
import com.algomau.bank.exception.NotFoundException;
import com.algomau.bank.lib.BeanUtil;
import com.algomau.bank.validator.AccountRequestDtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    public AccountResponseDto getAccount(UUID id) {
        return modelMapper.map(accountRepository.findById(id), AccountResponseDto.class);
    }

    public List<AccountResponseDto> getAccounts() {
        return modelMapper.map(accountRepository.findAll(), List.class);
    }

    public AccountResponseDto addAccount(AccountRequestDto accountRequestDto) {
        var account = modelMapper.map(accountRequestDto, Account.class);
        return modelMapper.map(accountRepository.save(account), AccountResponseDto.class);
    }

    public AccountResponseDto updateAccount(AccountRequestDto accountRequestDto, UUID id) {
        AccountRequestDtoValidator.assertValid(accountRequestDto);
        Account savedAccount = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "account not found."));
        var account = modelMapper.map(accountRequestDto, Account.class);
        BeanUtils.copyProperties(account, savedAccount, "id");
        return modelMapper.map(accountRepository.save(savedAccount), AccountResponseDto.class);
    }

    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }
}
