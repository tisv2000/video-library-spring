package com.tisv2000.dto.person;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class PersonCreateEditDto {

    Integer movieId;

    @NotBlank(message = "Name must not be empty")
    String name;

    @NotNull(message = "Birthday must not be empty")
    LocalDate birthday;
}
