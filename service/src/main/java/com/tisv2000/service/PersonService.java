package com.tisv2000.service;

import com.tisv2000.database.entity.QMovie;
import com.tisv2000.database.entity.QPerson;
import com.tisv2000.database.querydsl.QPredicates;
import com.tisv2000.database.repository.PersonRepository;
import com.tisv2000.dto.movie.MovieFilterDto;
import com.tisv2000.dto.movie.MovieReadDto;
import com.tisv2000.dto.person.PersonCreateEditDto;
import com.tisv2000.dto.person.PersonFilterDto;
import com.tisv2000.dto.person.PersonReadDto;
import com.tisv2000.mapper.person.PersonCreateEditMapper;
import com.tisv2000.mapper.person.PersonReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.tisv2000.database.entity.QMovie.movie;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonReadMapper personReadMapper;
    private final PersonCreateEditMapper personCreateEditMapper;

    public List<PersonReadDto> findAll() {
        return personRepository.findAll().stream()
                .map(person -> personReadMapper.map(person))
                .toList();
    }

    public List<PersonReadDto> findAllByFilter(PersonFilterDto movieFilterDto) {
        var predicate = QPredicates.builder()
                .add(movieFilterDto.getName(), QPerson.person.name::containsIgnoreCase)
                .add(movieFilterDto.getBirthday(), QPerson.person.birthday::eq)
                .add(movieFilterDto.getMovieName(), movie.title::containsIgnoreCase)
                .build();
        return StreamSupport
                .stream(personRepository.findAll(predicate).spliterator(), false)
                .map(personReadMapper::map)
                .toList();
    }

    public Optional<PersonReadDto> findById(Integer id) {
        return personRepository.findById(id)
                .map(personReadMapper::map);
    }

    public PersonReadDto create(PersonCreateEditDto personCreateEditDto) {
//        return personRepository.save(personDto -> personCreateEditMapper.map(personDto))
        return Optional.of(personCreateEditDto)
                .map(personCreateEditMapper::map)
                .map(personRepository::save)
                .map(personReadMapper::map)
                .orElseThrow();
    }

    public PersonReadDto update(Integer id, PersonCreateEditDto personCreateEditDto) {
        return personRepository.findById(id)
                .map(existingPersonEntity -> personCreateEditMapper.map(personCreateEditDto, existingPersonEntity))
                .map(entityToUpdate -> personRepository.saveAndFlush(entityToUpdate))
                .map(updatedEntity -> personReadMapper.map(updatedEntity))
                // бросаем конкретное исключение? На каком уровне это будет определяться?
                .orElseThrow();
    }

    // TODO проанализировать, почему сначала написала так
//    public boolean delete(Integer id) {
//        personRepository.findById(id)
//                .ifPresent(person -> {
//                    personRepository.deleteById(id);
//                    personRepository.flush();
//                    return true;
//                });
//        return false;
//    }

    public boolean delete(Integer id) {
        return personRepository.findById(id)
                .map(entity -> {
                            personRepository.delete(entity);
                            personRepository.flush();
                            return true;
                        }
                ).orElse(false);
    }
}
