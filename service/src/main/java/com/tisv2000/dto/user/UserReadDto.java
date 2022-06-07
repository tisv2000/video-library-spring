package com.tisv2000.dto.user;

import com.tisv2000.database.entity.Gender;
import com.tisv2000.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Integer id;
    Role role;
    Gender gender;
    LocalDate birthdate;
    String name;
    String password;
    String email;
}
