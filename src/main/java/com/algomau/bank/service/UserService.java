package com.algomau.bank.service;

import com.algomau.bank.domain.User;
import com.algomau.bank.domain.UserAccount;
import com.algomau.bank.domain.repository.UserRepository;
import com.algomau.bank.dto.request.UserRequestDto;
import com.algomau.bank.dto.response.UserResponseDto;
import com.algomau.bank.exception.NotFoundException;
import com.algomau.bank.exception.UnauthorizedException;
import com.algomau.bank.lib.BeanUtil;
import com.algomau.bank.validator.UserRequestDtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.algomau.bank.lib.SecurityUtils.getCurrentUser;
import static com.algomau.bank.lib.SecurityUtils.getCurrentUserRoles;

public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserAccountService userAccountService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserAccountService userAccountService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userAccountService = userAccountService;
    }

    public UserResponseDto getUser(UUID id) {
        UserAccount byUserName = getUserAccount(id);
        return modelMapper.map(byUserName.getUser(), UserResponseDto.class);
    }

    public List<UserResponseDto> getAllUser() {
        return modelMapper.map(userRepository.findAll(), List.class);
    }

    public UserResponseDto createUser(UserRequestDto userResponseDto) {
        UserRequestDtoValidator.assertValid(userResponseDto);
        User user = modelMapper.map(userResponseDto, User.class);
        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    public UserResponseDto updateUser(UserRequestDto userResponseDto, UUID id) {
        UserRequestDtoValidator.assertValid(userResponseDto);
        UserAccount userAccount = getUserAccount(id);
        User user = modelMapper.map(userResponseDto, User.class);
        BeanUtils.copyProperties(user, userAccount.getUser(), "id");
        return modelMapper.map(userRepository.save(userAccount.getUser()), UserResponseDto.class);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    private UserAccount getUserAccount(UUID id) {
        UserAccount byUserName = userAccountService.findByUserName(getCurrentUser().getUsername());
        if (id != byUserName.getUser().getId()) {
            throw new UnauthorizedException("unauthorized", "user not authorized.");
        }
        return byUserName;
    }
}
