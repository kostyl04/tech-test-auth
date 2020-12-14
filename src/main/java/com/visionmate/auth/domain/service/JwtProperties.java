package com.visionmate.auth.domain.service;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
public class JwtProperties {

    @NonNull
    private final String issuer;
    @NonNull
    private final String audience;
    @NonNull
    private final Integer ttlHours;
    @NonNull
    private final String key;
    @NonNull
    private final String tokenHeader;
    @NonNull
    private final String tokenPrefix;

    public byte[] getKeyBytes() {
        return key.getBytes();
    }
}
