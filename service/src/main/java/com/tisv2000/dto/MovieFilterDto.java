package com.tisv2000.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieFilterDto {
    String title;
    Integer year;
    String country;
    String genre;
}
