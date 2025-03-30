package com.algomau.bank.service;

import com.algomau.bank.dto.request.TransactionRequestDto;
import com.algomau.bank.dto.response.TransactionResponseDto;
import com.algomau.bank.exception.BadRequestException;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class FundsService {

    private final TransactionService transactionService;
    private final ModelMapper modelMapper;

    public FundsService(TransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    public TransactionResponseDto depositFunds(TransactionRequestDto transactionRequestDto) {
        if (transactionRequestDto.getAmount() <= 0) {
            throw new BadRequestException("bad-request", "Amount must be greater than 0");
        }
        transactionRequestDto.setAmount(transactionRequestDto.getAmount() * -1);

        transactionRequestDto.setStatus(TransactionRequestDto.Status.COMPLETED);
        transactionRequestDto.setOrganizationName(null);
        transactionRequestDto.setItemName("DEPOSIT");
        TransactionResponseDto transaction = transactionService.createTransaction(transactionRequestDto);
        transaction.setAmount(transactionRequestDto.getAmount() * -1);

        return transaction;
    }

    public TransactionResponseDto withdrawFunds(TransactionRequestDto transactionRequestDto) {
        if (transactionRequestDto.getAmount() <= 0) {
            throw new BadRequestException("bad-request", "Amount must be greater than 0");
        }
        transactionRequestDto.setStatus(TransactionRequestDto.Status.COMPLETED);
        transactionRequestDto.setOrganizationName(null);
        transactionRequestDto.setItemName("WITHDRAW");
        return transactionService.createTransaction(transactionRequestDto);
    }

    public TransactionResponseDto transferFunds(TransactionRequestDto transactionRequestDto, UUID fromId, UUID toId) {
        if (transactionRequestDto.getAmount() <= 0) {
            throw new BadRequestException("bad-request", "Amount must be greater than 0");
        }
        transactionRequestDto.setStatus(TransactionRequestDto.Status.COMPLETED);
        transactionRequestDto.setOrganizationName(null);

        TransactionRequestDto fromTransaction = modelMapper.map(transactionRequestDto, TransactionRequestDto.class);
        fromTransaction.setAccountId(fromId);
        TransactionResponseDto transactionResponseDto = withdrawFunds(fromTransaction);

        TransactionRequestDto toTransaction = modelMapper.map(transactionRequestDto, TransactionRequestDto.class);
        fromTransaction.setAccountId(toId);
        depositFunds(toTransaction);

        return transactionResponseDto;
    }
}
