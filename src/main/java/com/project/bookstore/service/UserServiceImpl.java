package com.project.bookstore.service;

import com.project.bookstore.exception.RegistrationException;
import com.project.bookstore.mapper.UserMapper;
import com.project.bookstore.model.Role;
import com.project.bookstore.model.User;
import com.project.bookstore.model.dto.user.UserRegistrationRequestDto;
import com.project.bookstore.model.dto.user.UserResponseDto;
import com.project.bookstore.repository.RoleRepository;
import com.project.bookstore.repository.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(
            UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Cannot register user");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role role = roleRepository.findByName(Role.RoleName.ROLE_USER);
        user.setRoles(Set.of(role));
        return userMapper.toDto(userRepository.save(user));
    }
}
