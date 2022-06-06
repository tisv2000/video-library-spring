package com.tisv2000.service;

import com.tisv2000.database.repository.UserRepository;
import com.tisv2000.dto.user.UserCreateEditDto;
import com.tisv2000.dto.user.UserReadDto;
import com.tisv2000.mapper.user.UserCreateEditMapper;
import com.tisv2000.mapper.user.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;


    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    public UserReadDto create(UserCreateEditDto user) {
        return Optional.of(user)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(entity -> {
                            userRepository.delete(entity);
                            userRepository.flush();
                            return true;
                        }
                ).orElse(false);
    }
}
