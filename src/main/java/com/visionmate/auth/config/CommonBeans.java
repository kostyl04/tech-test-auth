package com.visionmate.auth.config;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jwt.proc.JWTProcessor;
import com.visionmate.auth.domain.service.JwtProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class CommonBeans {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWEEncrypter jweEncrypter(JwtProperties jwtProperties) throws KeyLengthException {
        return new DirectEncrypter(jwtProperties.getKeyBytes());
    }

    @Bean
    public JWTProcessor<SecurityContext> jwtProcessor(JwtProperties jwtProperties) {
        ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
        JWKSource<SecurityContext> jweKeySource = new ImmutableSecret<>(jwtProperties.getKeyBytes());
        JWEKeySelector<SecurityContext> jweKeySelector = new JWEDecryptionKeySelector<>(JWEAlgorithm.DIR,
                EncryptionMethod.A128CBC_HS256,
                jweKeySource);
        jwtProcessor.setJWEKeySelector(jweKeySelector);
        DefaultJWTClaimsVerifier<SecurityContext> securityContextDefaultJWTClaimsVerifier = new DefaultJWTClaimsVerifier<>(jwtProperties.getAudience(),
                null,
                Set.of("username", "sub", "roles"));
        jwtProcessor.setJWTClaimsSetVerifier(securityContextDefaultJWTClaimsVerifier);
        return jwtProcessor;
    }
}
