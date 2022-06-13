package com.tisv2000.dto.user;

import com.tisv2000.database.entity.Gender;
import com.tisv2000.database.entity.Role;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    Role role;
    Gender gender;
    LocalDate birthdate;
    String name;

//    TODO добавить валидацию для всех полей и сюда @NotBlank(groups = CreateAction.class)
    String rawPassword;

    String email;
}
