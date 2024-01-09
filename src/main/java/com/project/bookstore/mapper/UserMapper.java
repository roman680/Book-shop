package com.project.bookstore.mapper;

import com.project.bookstore.model.User;
import com.project.bookstore.model.dto.UserRegistrationRequestDto;
import com.project.bookstore.model.dto.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
