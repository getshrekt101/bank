package com.algomau.bank.service;

import com.algomau.bank.domain.Transaction;
import com.algomau.bank.domain.repository.TransactionRepository;
import com.algomau.bank.dto.request.TransactionRequestDto;
import com.algomau.bank.dto.response.TransactionResponseDto;
import com.algomau.bank.exception.NotFoundException;
import com.algomau.bank.lib.BeanUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public TransactionService(TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    public TransactionResponseDto getTransaction(UUID id) {
        return modelMapper.map(transactionRepository.findById(id), TransactionResponseDto.class);
    }

    public List<TransactionResponseDto> getTransactions() {
        return modelMapper.map(transactionRepository.findAll(), List.class);
    }

    public TransactionResponseDto createTransaction(TransactionRequestDto accountRequestDto) {
        var Transaction = modelMapper.map(accountRequestDto, Transaction.class);
        return modelMapper.map(transactionRepository.save(Transaction), TransactionResponseDto.class);
    }

    public TransactionResponseDto updateTransaction(TransactionRequestDto accountRequestDto, UUID id) {
        com.algomau.bank.domain.Transaction savedTransaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "transaction not found."));
        var Transaction = modelMapper.map(accountRequestDto, Transaction.class);
        BeanUtil.copyNonNullProperties(Transaction, savedTransaction, "id");
        return modelMapper.map(transactionRepository.save(savedTransaction), TransactionResponseDto.class);
    }

    public void deleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }
}
