package com.project.bookstore.controller;

import com.project.bookstore.exception.RegistrationException;
import com.project.bookstore.model.dto.UserLoginRequestDto;
import com.project.bookstore.model.dto.UserLoginResponseDto;
import com.project.bookstore.model.dto.UserRegistrationRequestDto;
import com.project.bookstore.model.dto.UserResponseDto;
import com.project.bookstore.security.jwt.AuthenticationService;
import com.project.bookstore.service.UserService;
import jakarta.validation.Valid;
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
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public UserResponseDto registerUser(
            @RequestBody UserRegistrationRequestDto requestDto) throws RegistrationException {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
