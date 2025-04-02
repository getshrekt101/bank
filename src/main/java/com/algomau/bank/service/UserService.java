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
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

import static com.algomau.bank.domain.UserAccount.Role.ROLE_USER;

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
        UserAccount userAccountBySecurityContext = userAccountService.getUserAccountBySecurityContext();
        User savedUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "user not found."));
        if (ROLE_USER.equals(userAccountBySecurityContext.getRole()) &&
                !savedUser.getId().equals(userAccountBySecurityContext.getUser().getId())) {
            throw new UnauthorizedException("unauthorized", "user not authorized.");
        }
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    public List<UserResponseDto> getAllUser() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserResponseDto>>() {}.getType());
    }

    public UserResponseDto createUser(UserRequestDto userResponseDto) {
        UserRequestDtoValidator.assertValid(userResponseDto);
        User user = modelMapper.map(userResponseDto, User.class);
        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    public UserResponseDto updateUser(UserRequestDto requestDto, UUID id) {
        UserRequestDtoValidator.assertValid(requestDto);
        UserAccount userAccountBySecurityContext = userAccountService.getUserAccountBySecurityContext();
        User savedUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "user not found."));

        if (ROLE_USER.equals(userAccountBySecurityContext.getRole()) &&
                !savedUser.getId().equals(userAccountBySecurityContext.getUser().getId())) {
            throw new UnauthorizedException("unauthorized", "user not authorized.");
        }

        User user = modelMapper.map(requestDto, User.class);

        if (user.getAddress() != null) {
            modelMapper.map(user.getAddress(), savedUser.getAddress());
        }

        modelMapper.map(user, savedUser);

        return modelMapper.map(userRepository.save(savedUser), UserResponseDto.class);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
