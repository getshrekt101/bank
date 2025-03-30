package com.algomau.bank.service;

import com.algomau.bank.domain.Account;
import com.algomau.bank.domain.Transaction;
import com.algomau.bank.domain.UserAccount;
import com.algomau.bank.domain.repository.AccountRepository;
import com.algomau.bank.domain.repository.TransactionRepository;
import com.algomau.bank.dto.request.TransactionRequestDto;
import com.algomau.bank.dto.response.TransactionResponseDto;
import com.algomau.bank.exception.NotFoundException;
import com.algomau.bank.exception.UnauthorizedException;
import com.algomau.bank.validator.TransactionRequestDtoValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.UUID;

import static com.algomau.bank.domain.UserAccount.Role.ROLE_USER;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    private final UserAccountService userAccountService;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, ModelMapper modelMapper, UserAccountService userAccountService, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
        this.userAccountService = userAccountService;
        this.accountRepository = accountRepository;
    }

    public TransactionResponseDto getTransaction(UUID id) {
        UserAccount userAccount = userAccountService.getUserAccountBySecurityContext();
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "transaction not found."));
        if (ROLE_USER.equals(userAccount.getRole()) &&
                userAccount.user.getId() != transaction.getAccount().getId()) {
            throw new UnauthorizedException("unauthorized", "user not authorized.");
        }
        return modelMapper.map(transaction, TransactionResponseDto.class);
    }

    public List<TransactionResponseDto> getTransactions() {
        return modelMapper.map(transactionRepository.findAll(), new TypeToken<List<TransactionResponseDto>>() {}.getType());
    }

    public TransactionResponseDto createTransaction(TransactionRequestDto accountRequestDto) {
        TransactionRequestDtoValidator.assertValid(accountRequestDto);
        var transaction = modelMapper.map(accountRequestDto, Transaction.class);
        Account account = accountRepository.findById(accountRequestDto.getAccountId()).orElseThrow(() -> new NotFoundException("not-found", "account not found."));
        transaction.setAccount(account);
        transaction.getAccount().setBalance(transaction.getAccount().getBalance() - transaction.getAmount());
        return modelMapper.map(transactionRepository.save(transaction), TransactionResponseDto.class);
    }

    public TransactionResponseDto updateTransaction(TransactionRequestDto transactionRequestDto, UUID id) {
        TransactionRequestDtoValidator.assertValid(transactionRequestDto);
        com.algomau.bank.domain.Transaction savedTransaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "transaction not found."));
        var transaction = modelMapper.map(transactionRequestDto, Transaction.class);
        Account account = savedTransaction.getAccount();
        account.setBalance(account.getBalance() - savedTransaction.getAmount() + transactionRequestDto.getAmount());
        transaction.setAccount(account);
        return modelMapper.map(transactionRepository.save(savedTransaction), TransactionResponseDto.class);
    }

    public void deleteTransaction(UUID id) {
        com.algomau.bank.domain.Transaction savedTransaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "transaction not found."));
        savedTransaction.getAccount().setBalance(savedTransaction.getAccount().getBalance() - savedTransaction.getAmount());
        accountRepository.save(savedTransaction.getAccount());
        transactionRepository.deleteById(id);
    }

}
