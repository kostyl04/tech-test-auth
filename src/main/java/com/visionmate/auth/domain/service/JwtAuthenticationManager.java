package com.visionmate.auth.domain.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.JWTProcessor;
import com.visionmate.auth.config.JwtTokenAuthentication;
import com.visionmate.auth.util.exception.UnauthenticatedException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class JwtAuthenticationManager implements AuthenticationManager {

    private final JWTProcessor<SecurityContext> jwtProcessor;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof JwtTokenAuthentication) || isNull(authentication.getCredentials())) {
            throw new UnauthenticatedException("authentication.could.not.be.processed");
        }
        JwtTokenAuthentication jwtTokenAuthentication = (JwtTokenAuthentication) authentication;
        String credentials = jwtTokenAuthentication.getCredentials().toString();
        try {
            JWTClaimsSet claimsSet = jwtProcessor.process(credentials, null);
            jwtTokenAuthentication.setAuthenticated(true);
            jwtTokenAuthentication.setClaimsSet(claimsSet);
        } catch (ParseException | BadJOSEException | JOSEException e) {
            throw new UnauthenticatedException("bad.authentication");
        }
        return jwtTokenAuthentication;
    }
}
