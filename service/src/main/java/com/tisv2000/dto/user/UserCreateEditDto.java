package com.tisv2000.dto.user;

import com.tisv2000.database.entity.Gender;
import com.tisv2000.database.entity.Role;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
public class UserCreateEditDto {

    @NotBlank(message = "Name must not be empty")
    String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birthdate must not be empty")
    LocalDate birthdate;

    @NotNull(message = "Gender must not be empty")
    Gender gender;

    @NotNull(message = "Role must not be empty")
    Role role;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20")
    String rawPassword;

    @Email(message = "Email format is incorrect")
    @NotBlank(message = "Email must not be empty")
    String email;
}
