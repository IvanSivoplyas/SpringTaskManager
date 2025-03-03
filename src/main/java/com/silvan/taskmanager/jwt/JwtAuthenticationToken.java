package com.silvan.taskmanager.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    public JwtAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities, Collection<? extends GrantedAuthority> userDetailsAuthorities) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null; // Учетные данные не используются
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
