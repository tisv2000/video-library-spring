package com.tisv2000.database.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    // зачем это надо?
    @Override
    public String getAuthority() {
        return name();
    }
}
