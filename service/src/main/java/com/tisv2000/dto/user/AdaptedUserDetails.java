package com.tisv2000.dto.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AdaptedUserDetails extends User {

    public Integer id;

    public Integer getId() {
        return id;
    }

    public AdaptedUserDetails(Integer id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

}
