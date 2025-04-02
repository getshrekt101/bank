package com.algomau.bank.service;

import com.algomau.bank.domain.Account;
import com.algomau.bank.domain.Bank;
import com.algomau.bank.domain.User;
import com.algomau.bank.domain.repository.AccountRepository;
import com.algomau.bank.domain.repository.BankRepository;
import com.algomau.bank.domain.repository.UserRepository;
import com.algomau.bank.dto.request.AccountRequestDto;
import com.algomau.bank.dto.response.AccountResponseDto;
import com.algomau.bank.exception.NotFoundException;
import com.algomau.bank.lib.BeanUtil;
import com.algomau.bank.validator.AccountRequestDtoValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BankRepository bankRepository;

    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper, UserRepository userRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
    }

    public AccountResponseDto getAccount(UUID id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "account not found."));
        return modelMapper.map(account, AccountResponseDto.class);
    }

    public List<AccountResponseDto> getAccounts() {
        return modelMapper.map(accountRepository.findAll(),  new TypeToken<List<AccountResponseDto>>() {}.getType());
    }

    public AccountResponseDto addAccount(AccountRequestDto accountRequestDto) {
        var account = modelMapper.map(accountRequestDto, Account.class);
        User user = userRepository.findById(accountRequestDto.getUserId()).orElseThrow(() -> new NotFoundException("not-found", "user not found."));
        Bank bank = bankRepository.findById(accountRequestDto.getBankId()).orElseThrow(() -> new NotFoundException("not-found", "bank not found."));
        account.setBank(bank);
        account.setUser(user);
        return modelMapper.map(accountRepository.save(account), AccountResponseDto.class);
    }

    public AccountResponseDto updateAccount(AccountRequestDto accountRequestDto, UUID id) {
        AccountRequestDtoValidator.assertValid(accountRequestDto);
        Account savedAccount = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "account not found."));
        User user = userRepository.findById(accountRequestDto.getUserId()).orElseThrow(() -> new NotFoundException("not-found", "user not found."));
        Bank bank = bankRepository.findById(accountRequestDto.getBankId()).orElseThrow(() -> new NotFoundException("not-found", "bank not found."));
        savedAccount.setBank(bank);
        savedAccount.setUser(user);
        modelMapper.map(modelMapper.map(accountRequestDto, Account.class), savedAccount);
        return modelMapper.map(accountRepository.save(savedAccount), AccountResponseDto.class);
    }

    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }
}
