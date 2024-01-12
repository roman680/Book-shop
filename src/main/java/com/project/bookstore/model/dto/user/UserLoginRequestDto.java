package com.project.bookstore.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

@Data
public class UserLoginRequestDto {
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password should not be empty")
    @Size(max = 20, message = "Password should not exceed 20 characters")
    private String password;
}
