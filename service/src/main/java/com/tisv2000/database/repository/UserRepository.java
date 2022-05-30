package com.tisv2000.database.repository;

import com.tisv2000.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

//    Optional<User> findById(Integer id);

//    List<User> findAll();

//    User save(User entity);

//    User saveAndFlush(User entity);

//    void deleteById(Integer id);
}
