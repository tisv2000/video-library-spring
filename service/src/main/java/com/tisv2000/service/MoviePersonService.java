package com.tisv2000.service;

import com.tisv2000.database.repository.MoviePersonRepository;
import com.tisv2000.dto.movieperson.MoviePersonCreateEditDto;
import com.tisv2000.dto.movieperson.MoviePersonReadDto;
import com.tisv2000.mapper.movieperson.MoviePersonCreateEditMapper;
import com.tisv2000.mapper.movieperson.MoviePersonReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoviePersonService {

    private final MoviePersonRepository moviePersonRepository;
    private final MoviePersonReadMapper moviePersonReadMapper;
    private final MoviePersonCreateEditMapper moviePersonCreateEditMapper;

    public List<MoviePersonReadDto> findMoviePersonsByPersonId(Integer id) {
        return moviePersonRepository.findAllByPersonId(id).stream()
                .map(moviePersonReadMapper::map)
                .toList();
    }

    public List<MoviePersonReadDto> findMoviePersonsByMovieId(Integer id) {
        return moviePersonRepository.findAllByMovieId(id).stream()
                .map(moviePersonReadMapper::map)
                .toList();
    }

    public MoviePersonReadDto create(MoviePersonCreateEditDto moviePersonCreateEditDto) {
        return Optional.of(moviePersonCreateEditDto)
                .map(moviePersonCreateEditMapper::map)
                .map(moviePersonRepository::saveAndFlush)
                .map(moviePersonReadMapper::map)
                .orElseThrow();
    }
}
