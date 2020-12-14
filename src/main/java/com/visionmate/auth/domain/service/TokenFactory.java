package com.visionmate.auth.domain.service;

import com.visionmate.auth.domain.entity.AccessToken;
import com.visionmate.auth.domain.entity.Client;
import com.visionmate.auth.domain.entity.User;
import com.visionmate.auth.util.exception.BadParameterException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class TokenFactory {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ClientService clientService;

    public AccessToken create(AccessToken accessToken) {
        User user = userService.getByUsername(accessToken.getUsername());
        checkUser(user, accessToken);
        Client client = clientService.getClient(accessToken.getClientId());
        checkClient(client, accessToken);
        String jwt = jwtTokenService.createJwt(user.getName(), client.getId(), user.getPermissions());
        accessToken.setAccessToken(jwt);
        return accessToken;
    }

    public AccessToken create(User user, Client client) {
        String jwt = jwtTokenService.createJwt(user.getName(), client.getId(), user.getPermissions());
        return new AccessToken(jwt);
    }

    private void checkClient(Client client, AccessToken accessToken) {
        if (isNull(client) || !passwordEncoder.matches(accessToken.getClientSecret(), client.getSecret())) {
            throw new BadParameterException("invalid.username.or.password");
        }
    }

    private void checkUser(User user, AccessToken accessToken) {
        if (isNull(user) || !passwordEncoder.matches(accessToken.getPassword(), user.getPassword())) {
            throw new BadParameterException("invalid.username.or.password");
        }
    }
}