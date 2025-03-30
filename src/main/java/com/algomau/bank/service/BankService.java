package com.algomau.bank.service;

import com.algomau.bank.domain.Bank;
import com.algomau.bank.domain.repository.BankRepository;
import com.algomau.bank.dto.request.BankRequestDto;
import com.algomau.bank.dto.response.BankResponseDto;
import com.algomau.bank.exception.NotFoundException;
import com.algomau.bank.validator.BankRequestDtoValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.UUID;

public class BankService {

    private final BankRepository bankRepository;
    private final ModelMapper modelMapper;

    public BankService(BankRepository bankRepository, ModelMapper modelMapper) {
        this.bankRepository = bankRepository;
        this.modelMapper = modelMapper;
    }

    public BankResponseDto getBank(UUID id) {
        return modelMapper.map(bankRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "bank not found.")), BankResponseDto.class);
    }

    public List<BankResponseDto> getBanks() {
        return modelMapper.map(bankRepository.findAll(), new TypeToken<List<Bank>>() {}.getType());

    }

    public BankResponseDto createBank(BankRequestDto bankRequestDto) {
        BankRequestDtoValidator.assertValid(bankRequestDto);
        Bank save = bankRepository.save(modelMapper.map(bankRequestDto, Bank.class));
        return modelMapper.map(save, BankResponseDto.class);
    }

    public BankResponseDto updateBank(BankRequestDto bankRequestDto, UUID id) {
        BankRequestDtoValidator.assertValid(bankRequestDto);
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "bank not found."));
        Bank request = modelMapper.map(bankRequestDto, Bank.class);
        return modelMapper.map(bankRepository.save(request), BankResponseDto.class);
    }

    public void deleteBank(UUID id) {
        bankRepository.deleteById(id);
    }
}
