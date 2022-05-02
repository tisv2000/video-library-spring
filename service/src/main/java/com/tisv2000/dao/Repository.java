package com.tisv2000.dao;

import com.tisv2000.entity.EntityBase;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E extends EntityBase<K>> {

    E save(E entity);

    void update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

    void delete(K id);
}
