package com.tisv2000.dto.person;

import lombok.Value;

import java.time.LocalDate;

@Value
public class PersonCreateEditDto {
    String name;
    LocalDate birthday;
}
