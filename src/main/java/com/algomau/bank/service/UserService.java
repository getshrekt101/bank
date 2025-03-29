package com.algomau.bank.service;

import com.algomau.bank.domain.User;
import com.algomau.bank.domain.repository.UserRepository;
import com.algomau.bank.dto.request.UserRequestDto;
import com.algomau.bank.dto.response.UserResponseDto;
import com.algomau.bank.exception.NotFoundException;
import com.algomau.bank.lib.BeanUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponseDto getUser(UUID id) {
        return modelMapper.map(userRepository.findById(id), UserResponseDto.class);
    }

    public List<UserResponseDto> getAllUser() {
        return modelMapper.map(userRepository.findAll(), List.class);
    }

    public UserResponseDto createUser(UserRequestDto userResponseDto) {
        User user = modelMapper.map(userResponseDto, User.class);
        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    public UserResponseDto updateUser(UserRequestDto userResponseDto, UUID id) {
        User savedUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("not-found", "user not found."));
        User user = modelMapper.map(userResponseDto, User.class);
        BeanUtil.copyNonNullProperties(user, savedUser, "id");
        return modelMapper.map(userRepository.save(savedUser), UserResponseDto.class);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
