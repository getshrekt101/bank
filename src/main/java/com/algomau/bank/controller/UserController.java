package com.algomau.bank.controller;

import com.algomau.bank.dto.request.UserRequestDto;
import com.algomau.bank.dto.response.UserResponseDto;
import com.algomau.bank.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable UUID id) {
        UserResponseDto response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> response = userService.getAllUser();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userResponseDto) {
        UserResponseDto response = userService.createUser(userResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TELLER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserRequestDto userResponseDto) {
        UserResponseDto response = userService.updateUser(userResponseDto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
