package com.tisv2000.mapper.person;

import com.tisv2000.database.entity.Person;
import com.tisv2000.dto.person.PersonCreateEditDto;
import com.tisv2000.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PersonCreateEditMapper implements Mapper<PersonCreateEditDto, Person> {

    @Override
    public Person map(PersonCreateEditDto fromObject, Person toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Person map(PersonCreateEditDto object) {
        var person = new Person();
        copy(object, person);
        return person;
    }

    private void copy(PersonCreateEditDto fromObject, Person toObject) {
        toObject.setName(fromObject.getName());
        toObject.setBirthday(fromObject.getBirthday());
    }
}
