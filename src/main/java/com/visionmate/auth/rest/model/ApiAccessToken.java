package com.visionmate.auth.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
public class ApiAccessToken {

    @JsonProperty(access = WRITE_ONLY)
    private String username;
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    @JsonProperty(access = WRITE_ONLY)
    private String clientId;
    @JsonProperty(access = WRITE_ONLY)
    private String clientSecret;

    @JsonProperty(access = READ_ONLY)
    private String accessToken;
}
