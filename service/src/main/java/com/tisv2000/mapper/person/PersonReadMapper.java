package com.tisv2000.mapper.person;

import com.tisv2000.database.entity.Person;
import com.tisv2000.dto.person.PersonReadDto;
import com.tisv2000.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonReadMapper implements Mapper<Person, PersonReadDto> {

    @Override
    public PersonReadDto map(Person object) {
        return new PersonReadDto(
                object.getId(),
                object.getName(),
                object.getBirthday()
        );
    }
}
