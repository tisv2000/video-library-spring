package com.tisv2000.service;

import com.tisv2000.database.repository.ReviewRepository;
import com.tisv2000.dto.review.ReviewCreateEditDto;
import com.tisv2000.dto.review.ReviewReadDto;
import com.tisv2000.mapper.review.ReviewCreateEditMapper;
import com.tisv2000.mapper.review.ReviewReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewReadMapper reviewReadMapper;
    private final ReviewCreateEditMapper reviewCreateEditMapper;

    public List<ReviewReadDto> findAll() {
        return reviewRepository.findAll().stream()
                .map(reviewReadMapper::map)
                .toList();
    }

    public Optional<ReviewReadDto> findById(Integer id) {
        return reviewRepository.findById(id)
                .map(reviewReadMapper::map);
    }

    public ReviewReadDto create(ReviewCreateEditDto reviewCreateEditDto) {
        // в чем разница между Optional.of() и Optional.ofNullable()
        return Optional.of(reviewCreateEditDto)
                .map(reviewCreateEditMapper::map)
                .map(reviewRepository::save)
                .map(reviewReadMapper::map)
                .orElseThrow();
    }

    public ReviewReadDto update(Integer id, ReviewCreateEditDto reviewCreateEditDto) {
        return reviewRepository.findById(id)
                .map(entity -> reviewCreateEditMapper.map(reviewCreateEditDto, entity))
                .map(reviewRepository::saveAndFlush)
                .map(reviewReadMapper::map)
                .orElseThrow();
    }

    public boolean delete(Integer id) {
        return reviewRepository.findById(id)
                .map(entity -> {
                            reviewRepository.delete(entity);
                            reviewRepository.flush();
                            return true;
                        }
                ).orElse(false);
    }
}
