package com.tisv2000.service;

import com.tisv2000.database.querydsl.QPredicates;
import com.tisv2000.database.repository.MovieRepository;
import com.tisv2000.dto.movie.MovieCreateEditDto;
import com.tisv2000.dto.movie.MovieFilterDto;
import com.tisv2000.dto.movie.MovieReadDto;
import com.tisv2000.mapper.movie.MovieCreateEditMapper;
import com.tisv2000.mapper.movie.MovieReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.tisv2000.database.entity.QMovie.movie;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieReadMapper movieReadMapper;
    private final MovieCreateEditMapper movieCreateEditMapper;

    public List<MovieReadDto> findAll() {
        return movieRepository.findAll().stream()
                .map(movieReadMapper::map)
                .toList();
    }

    public List<MovieReadDto> findAllByFilter(MovieFilterDto movieFilterDto) {
        var predicate = QPredicates.builder()
                .add(movieFilterDto.getTitle(), movie.country::containsIgnoreCase)
                .add(movieFilterDto.getYear(), movie.year::eq)
                .add(movieFilterDto.getCountry(), movie.country::containsIgnoreCase)
                .add(movieFilterDto.getGenre(), movie.genre::eq)
                .build();
        return StreamSupport
                .stream(movieRepository.findAll(predicate).spliterator(), false)
                .map(movieReadMapper::map)
                .toList();
    }

    public Optional<MovieReadDto> findById(Integer id) {
        return movieRepository.findById(id)
                .map(movieReadMapper::map);
    }

    @Transactional
    public MovieReadDto create(MovieCreateEditDto movieDto) {
        return Optional.of(movieDto)
                .map(movieCreateEditMapper::map)
                .map(movieRepository::save)
                .map(movieReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<MovieReadDto> update(Integer id, MovieCreateEditDto movieDto) {
        return movieRepository.findById(id)
                // closure
                .map(entity -> movieCreateEditMapper.map(movieDto, entity))
                .map(movieRepository::saveAndFlush)
                .map(movieReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return movieRepository.findById(id)
                .map(entity -> {
                            movieRepository.delete(entity);
                            movieRepository.flush();
                            return true;
                        }
                )
                .orElse(false);
    }
}
