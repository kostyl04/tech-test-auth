package com.visionmate.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public class UsernamePasswordTokenSecurityContext implements SecurityContext {

    private Authentication authentication;

    @Override
    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
