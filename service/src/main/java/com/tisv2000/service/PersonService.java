package com.tisv2000.service;

import com.tisv2000.database.repository.PersonRepository;
import com.tisv2000.dto.person.PersonCreateEditDto;
import com.tisv2000.dto.person.PersonReadDto;
import com.tisv2000.mapper.person.PersonCreateEditMapper;
import com.tisv2000.mapper.person.PersonReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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

    public Optional<PersonReadDto> findById(Integer id) {
        return personRepository.findById(id)
                .map(person -> personReadMapper.map(person));
    }

    public PersonReadDto create(PersonCreateEditDto personCreateEditDto) {
//        return personRepository.save(personDto -> personCreateEditMapper.map(personDto))
        return Optional.of(personCreateEditDto)
                .map(personDto -> personCreateEditMapper.map(personDto))
                .map(personEntity -> personRepository.save(personEntity))
                .map(savedEntity -> personReadMapper.map(savedEntity))
                // бросаем конкретное исключение? На каком уровне это будет определяться?
                .orElseThrow();
    }

    public PersonReadDto update(PersonCreateEditDto personCreateEditDto, Integer id) {
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
