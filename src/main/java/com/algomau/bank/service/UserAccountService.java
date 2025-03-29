package com.algomau.bank.service;

import com.algomau.bank.domain.UserAccount;
import com.algomau.bank.domain.repository.UserAccountRepository;
import com.algomau.bank.dto.request.UserAccountRequestDto;
import com.algomau.bank.dto.response.UserAccountResponseDto;
import com.algomau.bank.exception.NotFoundException;
import com.algomau.bank.lib.BeanUtil;
import com.algomau.bank.validator.AccountRequestDtoValidator;
import com.algomau.bank.validator.UserAccountRequestDtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserAccountService(UserAccountRepository userAccountRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccountResponseDto getUserAccount(UUID id) {
        return modelMapper.map(userAccountRepository.findById(id), UserAccountResponseDto.class);
    }

    public List<UserAccountResponseDto> getUserAccounts() {
        return modelMapper.map(userAccountRepository.findAll(), List.class);
    }

    public UserAccountResponseDto createUserAccount(UserAccountRequestDto userAccount) {
        UserAccountRequestDtoValidator.assertValid(userAccount);
        UserAccount user = modelMapper.map(userAccount, UserAccount.class);
        return modelMapper.map(userAccountRepository.save(user), UserAccountResponseDto.class);
    }

    public UserAccountResponseDto updateUserAccount(UserAccountRequestDto userAccount, UUID id) {
        UserAccountRequestDtoValidator.assertValid(userAccount);
        UserAccount savedUserAccount = userAccountRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "user-account not found."));
        UserAccount user = modelMapper.map(userAccount, UserAccount.class);
        BeanUtils.copyProperties(user, savedUserAccount, "id");
        return modelMapper.map(userAccountRepository.save(savedUserAccount), UserAccountResponseDto.class);
    }

    public void deleteUserAccount(UUID id) {
        userAccountRepository.deleteById(id);
    }

    public UserAccount findByUserName(String username) {
        return userAccountRepository.findByUserName(username);
    }
}
