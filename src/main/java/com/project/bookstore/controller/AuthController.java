package com.project.bookstore.controller;

import com.project.bookstore.exception.RegistrationException;
import com.project.bookstore.model.dto.UserRegistrationRequestDto;
import com.project.bookstore.model.dto.UserResponseDto;
import com.project.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserResponseDto registerUser(
            @RequestBody UserRegistrationRequestDto requestDto) throws RegistrationException {
        return userService.register(requestDto);
    }
}
