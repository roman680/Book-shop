package com.project.bookstore.service;

import com.project.bookstore.exception.RegistrationException;
import com.project.bookstore.model.dto.UserRegistrationRequestDto;
import com.project.bookstore.model.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
