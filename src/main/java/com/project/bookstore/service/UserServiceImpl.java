package com.project.bookstore.service;

import com.project.bookstore.exception.RegistrationException;
import com.project.bookstore.mapper.UserMapper;
import com.project.bookstore.model.User;
import com.project.bookstore.model.dto.UserRegistrationRequestDto;
import com.project.bookstore.model.dto.UserResponseDto;
import com.project.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(
            UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Cannot register user");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        return userMapper.toDto(userRepository.save(user));
    }
}
