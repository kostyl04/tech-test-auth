package com.visionmate.auth.config;

import com.visionmate.auth.domain.service.JwtProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

    private final String tokenHeader;
    private final String tokenPrefix;
    private final AuthenticationManager authenticationManager;

    public JwtSecurityFilter(JwtProperties jwtProperties, AuthenticationManager authenticationManager) {
        this.tokenHeader = jwtProperties.getTokenHeader();
        this.tokenPrefix = jwtProperties.getTokenPrefix() + " ";
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(tokenHeader);
        String token = null;
        if (isNotEmpty(header) && header.startsWith(tokenPrefix)) {
            token = header.substring(7);
            Authentication authenticate = authenticationManager.authenticate(new JwtTokenAuthentication(token, httpServletRequest.getRemoteAddr()));
            var securityContext = new UsernamePasswordTokenSecurityContext();
            securityContext.setAuthentication(authenticate);
            SecurityContextHolder.setContext(securityContext);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
