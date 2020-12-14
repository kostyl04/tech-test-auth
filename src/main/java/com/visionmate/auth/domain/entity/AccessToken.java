package com.visionmate.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessToken {

    private String username;
    private String password;
    private String clientId;
    private String clientSecret;

    private String accessToken;

    public AccessToken() {
    }

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
