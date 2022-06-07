package com.tisv2000.mapper.user;

import com.tisv2000.database.entity.User;
import com.tisv2000.dto.user.UserReadDto;
import com.tisv2000.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getRole(),
                object.getGender(),
                object.getBirthdate(),
                object.getName(),
                object.getPassword(),
                object.getEmail()
        );
    }
}
