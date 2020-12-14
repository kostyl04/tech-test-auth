package com.visionmate.auth.domain.service;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.visionmate.auth.domain.entity.Permission.PermissionName;
import com.visionmate.auth.util.exception.UnexpectedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class JwtTokenService {

    private final JWEEncrypter encrypter;
    private final JwtProperties jwtProperties;

    public String createJwt(String username, String clientId, List<PermissionName> roles) {
        JWEHeader jweHeader = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, jwtProperties.getTtlHours());
        JWTClaimsSet jwt = new JWTClaimsSet.Builder().audience(jwtProperties.getAudience())
                .issuer(jwtProperties.getIssuer())
                .issueTime(new Date())
                .expirationTime(calendar.getTime())
                .subject(clientId)
                .claim("username", username)
                .claim("roles", roles)
                .build();
        JWEObject jweObject = new JWEObject(jweHeader, new Payload(jwt.toJSONObject()));
        try {
            jweObject.encrypt(encrypter);
        } catch (JOSEException e) {
            throw new UnexpectedException("jwe.encryption.failed", e);
        }
        return jweObject.serialize();
    }
}
