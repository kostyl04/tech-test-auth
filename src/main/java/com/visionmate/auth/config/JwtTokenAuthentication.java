package com.visionmate.auth.config;

import com.nimbusds.jwt.JWTClaimsSet;
import com.visionmate.auth.util.exception.UnexpectedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

public class JwtTokenAuthentication implements Authentication {

    private final String token;
    private final String ip;


    private JWTClaimsSet claimsSet;
    private List<GrantedAuthority> authorities;
    private boolean authenticated;

    public JwtTokenAuthentication(String token, String ip) {
        this.token = token;
        this.ip = ip;
    }

    public void setClaimsSet(JWTClaimsSet claimsSet) {
        this.claimsSet = claimsSet;
        try {
            authorities = claimsSet.getStringListClaim("roles").stream().map(SimpleGrantedAuthority::new).collect(toList());
        } catch (ParseException e) {
            throw new UnexpectedException("claims.set.parse.exception", e);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isNull(authorities)) {
            authorities = new ArrayList<>();
        }
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return ip;
    }

    @Override
    public Object getPrincipal() {
        return claimsSet.getClaim("username");
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return "JWT";
    }
}
