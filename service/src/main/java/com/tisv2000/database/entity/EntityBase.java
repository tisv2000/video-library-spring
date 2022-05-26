package com.tisv2000.database.entity;

import java.io.Serializable;

public interface EntityBase<T extends Serializable> {

    T getId();

}
