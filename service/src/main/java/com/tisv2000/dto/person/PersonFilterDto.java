package com.tisv2000.dto.person;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class PersonFilterDto {
    String name;
    LocalDate birthday;
    String movieName;
}
