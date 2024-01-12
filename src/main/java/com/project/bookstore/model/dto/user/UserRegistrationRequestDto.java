package com.project.bookstore.model.dto.user;

import com.project.bookstore.validator.PasswordFieldsMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

@Data
@PasswordFieldsMatch
public class UserRegistrationRequestDto {
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password should not be empty")
    @Size(max = 20, message = "Password should not exceed 20 characters")
    private String password;

    private String repeatPassword;

    @NotBlank(message = "First name should not be empty")
    @Size(max = 255, message = "First name should not exceed 255 characters")
    private String firstName;

    @NotBlank(message = "Last name should not be empty")
    @Size(max = 255, message = "Last name should not exceed 255 characters")
    private String lastName;

    private String shippingAddress;
}
