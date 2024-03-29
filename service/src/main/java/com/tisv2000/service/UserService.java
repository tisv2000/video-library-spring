package com.tisv2000.service;

import com.tisv2000.database.entity.QUser;
import com.tisv2000.database.querydsl.QPredicates;
import com.tisv2000.database.repository.UserRepository;
import com.tisv2000.dto.movie.MovieFilterDto;
import com.tisv2000.dto.movie.MovieReadDto;
import com.tisv2000.dto.user.AdaptedUserDetails;
import com.tisv2000.dto.user.UserCreateEditDto;
import com.tisv2000.dto.user.UserFilterDto;
import com.tisv2000.dto.user.UserReadDto;
import com.tisv2000.mapper.user.UserCreateEditMapper;
import com.tisv2000.mapper.user.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.tisv2000.database.entity.QMovie.movie;
import static com.tisv2000.database.entity.QUser.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

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

    public List<UserReadDto> findAllByFilter(UserFilterDto userFilterDto) {
        var predicate = QPredicates.builder()
                .add(userFilterDto.getName(), user.name::containsIgnoreCase)
                .add(userFilterDto.getBirthdate(), user.birthdate::eq)
                .add(userFilterDto.getEmail(), user.email::containsIgnoreCase)
                .build();
        return StreamSupport
                .stream(userRepository.findAll(predicate).spliterator(), false)
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findByName(String name) {
        return userRepository.findByName(name)
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> new AdaptedUserDetails(
                        user.getId(),
                        user.getName(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                )).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
